package com.stackroute.jobservice.serviceimpl;

import java.text.MessageFormat;
import com.stackroute.jobservice.rabbitmq.Producer;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.BsonTimestamp;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.stackroute.jobservice.dto.JobApplicationRequestDto;
import com.stackroute.jobservice.dto.JobApplicationResponseDto;
import com.stackroute.jobservice.dto.JobPostingResponseDto;
import com.stackroute.jobservice.dto.PaginationResponseDto;
import com.stackroute.jobservice.enums.JobApplicationStatus;
import com.stackroute.jobservice.exception.JobApplicationException;
import com.stackroute.jobservice.exception.JobPostingException;
import com.stackroute.jobservice.modal.JobApplication;
import com.stackroute.jobservice.modal.JobPosting;
import com.stackroute.jobservice.rabbitmq.EmailDTO;
import com.stackroute.jobservice.repository.JobApplicationRepository;
import com.stackroute.jobservice.repository.JobPostingRepository;
import com.stackroute.jobservice.service.JobApplicationService;
import com.stackroute.jobservice.utility.Constants;
import com.stackroute.jobservice.utility.Response;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	Logger logger = LoggerFactory.getLogger(JobApplicationServiceImpl.class);

	@Autowired
	Environment environment;

	@Autowired
	JobApplicationRepository jobApplicationRepository;

	@Autowired
	JobPostingRepository jobPostingRepository;

	@Autowired
	Producer producer;

	@Override
	public Object createJobApplication(JobApplicationRequestDto jobApplicationRequestDto) {
		try {
			logger.info("Inside createJobApplication of JobApplicationServiceImpl");
			if (jobPostingRepository.findById(jobApplicationRequestDto.getJobId()).isEmpty()) {
				logger.info("Job does not exist");
				throw new JobApplicationException(environment.getProperty(Constants.JOB_DOES_NOT_EXIST));
			}
			if (jobApplicationRepository.findByJobIdAndAppliedByAndStatus(jobApplicationRequestDto.getJobId(),
					jobApplicationRequestDto.getAppliedBy(), JobApplicationStatus.APPLIED) != null) {
				logger.info("you have already applied to this job");
				throw new JobApplicationException(environment.getProperty(Constants.ALREADY_APPLIED));
			}
			if (jobApplicationRepository.findByJobIdAndAppliedByAndStatus(jobApplicationRequestDto.getJobId(),
					jobApplicationRequestDto.getAppliedBy(), JobApplicationStatus.ACCEPTED) != null) {
				logger.info("your application is already accepted by employer");
				throw new JobApplicationException(environment.getProperty(Constants.ALREADY_ACCEPTED));
			}
			JobApplication savedJobApplication = jobApplicationRepository
					.save(getPopulatedJobApplication(jobApplicationRequestDto));
			logger.info("Job application saved successfully");
			sendMessageToRabbitMqForEmployer(savedJobApplication);
			return new Response<Object>(savedJobApplication, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_POSTED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addJob of JobPostingServiceImpl : {0}", e);
			logger.error(errorMsg);
			throw new JobApplicationException(e.getMessage());
		}
	}

	public JobApplication getPopulatedJobApplication(JobApplicationRequestDto jobApplicationRequestDto) {
		JobApplication jobApplication = new JobApplication();
		try {
			jobApplication.setAppliedBy(jobApplicationRequestDto.getAppliedBy());
			jobApplication.setCommentFromOrganisation(jobApplicationRequestDto.getCommentFromOrganisation());
			jobApplication.setCreatedOn(new BsonTimestamp(System.currentTimeMillis()));
			jobApplication.setExpectedSalary(jobApplicationRequestDto.getExpectedSalary());
			jobApplication.setJobId(jobApplicationRequestDto.getJobId());
			jobApplication.setStatus(JobApplicationStatus.APPLIED);
			try {
				if (jobApplicationRequestDto.getUpdatedResume() != null) {
					jobApplication.setUpdatedResume(new Binary(BsonBinarySubType.BINARY,
							jobApplicationRequestDto.getUpdatedResume().getBytes()));
				}
				if (jobApplicationRequestDto.getCoverLetter() != null) {
					jobApplication.setCoverLetter(
							new Binary(BsonBinarySubType.BINARY, jobApplicationRequestDto.getCoverLetter().getBytes()));

				}
			} catch (Exception e) {
				throw new JobApplicationException(
						environment.getProperty(Constants.UPDATED_RESUME_AND_COVER_LETTER_NOT_IN_PROPER_FORMAT));
			}
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getPopulatedJobApplication of JobApplicationsServiceImpl : {0}", e);
			logger.error(errorMsg);
			throw new JobApplicationException(e.getMessage());
		}
		return jobApplication;
	}

	@Override
	public Object updateJobApplication(JobApplicationRequestDto jobApplicationRequestDto) {
		try {
			logger.info("Inside updateJobApplication of JobApplicationServiceImpl");
			if (StringUtils.isEmpty(jobApplicationRequestDto.getJobApplicationId())) {
				logger.info("Invalid data");
				throw new JobApplicationException(environment.getProperty(Constants.INVALID_DATA));
			}
			JobApplication jobApplication = jobApplicationRepository
					.findByIdAndStatus(jobApplicationRequestDto.getJobApplicationId(), JobApplicationStatus.APPLIED);
			if (jobApplication == null) {
				logger.info("No Job Application  Found");
				throw new JobApplicationException(environment.getProperty(Constants.NO_JOB_APPLICATION_FOUND));
			}
			String statusOFJob = jobApplication.getStatus().name();
			logger.info("Status of job before saving");
			JobApplication updatedJobApplication = jobApplicationRepository
					.save(getPopulatedJobApplicationForUpdate(jobApplication, jobApplicationRequestDto));
			logger.info("Job posting updated successfully :{}", updatedJobApplication);
			if (!statusOFJob.equals(updatedJobApplication.getStatus().name())) {
				sendMessageToRabbitMqForSeeker(updatedJobApplication);
			}
			return new Response<Object>(updatedJobApplication, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_APPLICATION_UPDATED));
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in updateJobApplication of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobApplicationException(e.getMessage());
		}
	}

	public void sendMessageToRabbitMqForSeeker(JobApplication updatedJobApplication) {
		try {
			logger.info("Inside sendMessageToRabbitMqForSeeker for changing the status");
			JobPosting jobPosting = jobPostingRepository.findById(updatedJobApplication.getJobId()).get();
			EmailDTO emailDTO = new EmailDTO();
			String messageToSend = null;
			if (updatedJobApplication.getStatus().name().equals(JobApplicationStatus.ACCEPTED.name())) {
				messageToSend = MessageFormat.format(environment.getProperty(Constants.JOB_APPLICATION_ACCEPTED),
						updatedJobApplication.getId(), updatedJobApplication.getId(), jobPosting.getCompany(),
						jobPosting.getEmployerEmail());
			}
			if (updatedJobApplication.getStatus().name().equals(JobApplicationStatus.REJECTED.name())) {
				messageToSend = MessageFormat.format(environment.getProperty(Constants.JOB_APPLICATION_REJECTED),
						updatedJobApplication.getId(), updatedJobApplication.getId(), jobPosting.getCompany(),
						jobPosting.getEmployerEmail());
			}
			emailDTO.setMessage(messageToSend);
			emailDTO.setReceiver(updatedJobApplication.getAppliedBy());
			emailDTO.setSubject(Constants.STATUS_OF_YOUR_JOB_APPLICATION_CHANGED);
			logger.info("Email Dto for rabbit mq is :{}", emailDTO);
			producer.sendMessageToRabbitMq(emailDTO);
			logger.info("Message sent to rabbit mq");

		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in sendMessageToRabbitMqForSeeker of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			// throw new JobApplicationException(e.getMessage());

		}
	}

	public void sendMessageToRabbitMqForEmployer(JobApplication updatedJobApplication) {
		try {
			logger.info("Inside sendMessageToRabbitMqForEmployer for changing the status");
			JobPosting jobPosting = jobPostingRepository.findById(updatedJobApplication.getJobId()).get();
			EmailDTO emailDTO = new EmailDTO();
			String messageToSend = MessageFormat.format(
					environment.getProperty(Constants.JOB_APPLICATION_APPLIED_MESSAGE),
					updatedJobApplication.getAppliedBy(), updatedJobApplication.getId(),
					updatedJobApplication.getJobId(), jobPosting.getCompany(), jobPosting.getPosition());
			emailDTO.setMessage(messageToSend);
			emailDTO.setReceiver(jobPosting.getEmployerEmail());
			emailDTO.setSubject(Constants.NEW_JOB_APPLICATION_KINDLY_ACCEPT_OR_REJECT);
			logger.info("Email Dto for rabbit mq is :{}", emailDTO);
			producer.sendMessageToRabbitMq(emailDTO);
			logger.info("Message sent to rabbit mq");
		} catch (Exception e) {
			String errorMsg = MessageFormat.format(
					"Exception caught in sendMessageToRabbitMqForEmployer of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			// throw new JobApplicationException(e.getMessage());

		}
	}

	public JobApplication getPopulatedJobApplicationForUpdate(JobApplication jobApplication,
			JobApplicationRequestDto jobApplicationRequestDto) {
		logger.info("Inside getPopulatedJobApplicationForUpdate of JobApplicationServiceImpl");
		try {
			jobApplication.setId(jobApplicationRequestDto.getJobApplicationId());
			jobApplication.setAppliedBy(
					jobApplicationRequestDto.getAppliedBy() != null ? jobApplicationRequestDto.getAppliedBy()
							: jobApplication.getAppliedBy());
			jobApplication.setCommentFromOrganisation(jobApplicationRequestDto.getCommentFromOrganisation() != null
					? jobApplicationRequestDto.getCommentFromOrganisation()
					: jobApplication.getCommentFromOrganisation());
			jobApplication.setModifiedOn(new BsonTimestamp(System.currentTimeMillis()));
			jobApplication.setExpectedSalary(
					jobApplicationRequestDto.getExpectedSalary() != null ? jobApplicationRequestDto.getExpectedSalary()
							: jobApplication.getExpectedSalary());
			jobApplication.setJobId(jobApplicationRequestDto.getJobId() != null ? jobApplicationRequestDto.getJobId()
					: jobApplication.getJobId());
			jobApplication.setStatus(jobApplicationRequestDto.getStatus() != null ? jobApplicationRequestDto.getStatus()
					: jobApplication.getStatus());
			try {
				if (jobApplicationRequestDto.getUpdatedResume() != null) {
					jobApplication.setUpdatedResume(new Binary(BsonBinarySubType.BINARY,
							jobApplicationRequestDto.getUpdatedResume().getBytes()));
				} else {
					jobApplication.setUpdatedResume(jobApplication.getUpdatedResume());
				}
				if (jobApplicationRequestDto.getCoverLetter() != null) {
					jobApplication.setCoverLetter(
							new Binary(BsonBinarySubType.BINARY, jobApplicationRequestDto.getCoverLetter().getBytes()));

				} else {
					jobApplication.setCoverLetter(jobApplication.getCoverLetter());
				}
			} catch (Exception e) {
				throw new JobApplicationException(
						environment.getProperty(Constants.UPDATED_RESUME_AND_COVER_LETTER_NOT_IN_PROPER_FORMAT));
			}
		} catch (Exception e) {
			String errorMsg = MessageFormat.format(
					"Exception caught in getPopulatedJobApplicationForUpdate of JobApplicationsServiceImpl : {0}", e);
			logger.error(errorMsg);
			throw new JobApplicationException(e.getMessage());
		}
		return jobApplication;
	}

	@Override
	public Object getJobApplicationByEmail(String email, Pageable pageable) {
		try {
			logger.info("Inside getJobApplicationByEmail of JobApplicationServiceImpl");
			if (StringUtils.isEmpty(email)) {
				logger.info("Invalid data");
				throw new JobApplicationException(environment.getProperty(Constants.INVALID_DATA));
			}
			List<JobApplicationResponseDto> jobApplicationList = new ArrayList<>();
			Page<JobApplication> dataFromRepo = jobApplicationRepository.findByAppliedBy(email, pageable);
			if (dataFromRepo == null || dataFromRepo.isEmpty()) {
				logger.info("No Job Application found");
				throw new JobApplicationException(environment.getProperty(Constants.NO_JOB_APPLICATION_FOUND));
			}
			dataFromRepo
					.forEach(jobApplication -> jobApplicationList.add(populateJobApplicationResponse(jobApplication)));
			PaginationResponseDto<JobApplicationResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(jobApplicationList);
			paginationResponseDto.setPageable(dataFromRepo.getPageable());
			logger.info("Job Applications fetched successfully");
			paginationResponseDto.setTotalNoOfElements(dataFromRepo.getTotalElements());
			paginationResponseDto.setTotalPages(dataFromRepo.getTotalPages());
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_APPLICATION_FETCHED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getJobApplicationByEmail of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobApplicationException(e.getMessage());
		}
	}

	public JobApplicationResponseDto populateJobApplicationResponse(JobApplication jobApplication) {
		JobApplicationResponseDto jobApplicationResponseDto = new JobApplicationResponseDto();
		logger.info("Inside populateJobApplicationResponse of JobApplicationServiceImpl");
		try {
			jobApplicationResponseDto.setAppliedBy(jobApplication.getAppliedBy());
			jobApplicationResponseDto.setCommentFromOrganisation(jobApplication.getCommentFromOrganisation());
			jobApplicationResponseDto.setCoverLetter(jobApplication.getCoverLetter());
			jobApplicationResponseDto.setCreatedOn(
					jobApplication.getCreatedOn() != null ? jobApplication.getCreatedOn().getValue() : null);
			jobApplicationResponseDto.setExpectedSalary(jobApplication.getExpectedSalary());
			jobApplicationResponseDto.setJobApplicationId(jobApplication.getId());
			jobApplicationResponseDto.setJobId(jobApplication.getJobId());
			jobApplicationResponseDto.setModifiedOn(
					jobApplication.getModifiedOn() != null ? jobApplication.getModifiedOn().getValue() : null);
			jobApplicationResponseDto.setStatus(jobApplication.getStatus());
			jobApplicationResponseDto.setUpdatedResume(jobApplication.getUpdatedResume());
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in populateJobApplicationResponse of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobApplicationException(e.getMessage());
		}
		return jobApplicationResponseDto;
	}

	@Override
	public Object getAllJobApplications(String searchParam, Pageable pageable) {
		try {
			logger.info("Inside getAllJobApplications of JobApplicationServiceImpl");
			List<JobApplicationResponseDto> jobApplicationList = new ArrayList<>();
			Page<JobApplication> dataFromRepo = jobApplicationRepository
					.findByAppliedByLikeOrStatusLikeOrderByCreatedOnDesc(searchParam, searchParam, pageable);
			if (dataFromRepo == null || dataFromRepo.isEmpty()) {
				logger.info("No Job Application found");
				throw new JobApplicationException(environment.getProperty(Constants.NO_JOB_APPLICATION_FOUND));
			}
			dataFromRepo
					.forEach(jobApplication -> jobApplicationList.add(populateJobApplicationResponse(jobApplication)));
			PaginationResponseDto<JobApplicationResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(jobApplicationList);
			paginationResponseDto.setPageable(dataFromRepo.getPageable());
			paginationResponseDto.setTotalNoOfElements(dataFromRepo.getTotalElements());
			paginationResponseDto.setTotalPages(dataFromRepo.getTotalPages());
			logger.info("Job Applications fetched successfully");
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_APPLICATION_FETCHED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getAllJobApplications of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobApplicationException(e.getMessage());
		}
	}
}