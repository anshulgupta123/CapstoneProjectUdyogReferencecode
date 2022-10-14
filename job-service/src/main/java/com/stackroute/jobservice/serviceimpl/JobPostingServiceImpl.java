package com.stackroute.jobservice.serviceimpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.stackroute.jobservice.dto.CompanyRequestDto;
import com.stackroute.jobservice.dto.JobPostingRequestDto;
import com.stackroute.jobservice.dto.JobPostingResponseDto;
import com.stackroute.jobservice.dto.PaginationResponseDto;
import com.stackroute.jobservice.enums.JobStatus;
import com.stackroute.jobservice.exception.JobPostingException;
import com.stackroute.jobservice.modal.JobPosting;
import com.stackroute.jobservice.repository.CompanyRepository;
import com.stackroute.jobservice.repository.JobPostingRepository;
import com.stackroute.jobservice.service.CompanyService;
import com.stackroute.jobservice.service.JobPostingService;
import com.stackroute.jobservice.utility.Constants;
import com.stackroute.jobservice.utility.Response;

@Service
public class JobPostingServiceImpl implements JobPostingService {

	Logger logger = LoggerFactory.getLogger(JobPostingServiceImpl.class);

	@Autowired
	JobPostingRepository jobPostingRepository;

	@Autowired
	Environment environment;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CompanyService companyService;

	@Override
	public Object addJob(JobPostingRequestDto jobPostingRequestDto) {
		try {
			logger.info("Inside addJob of JobPostingServiceImpl");
			if (StringUtils.isEmpty(jobPostingRequestDto.getCompany())) {
				throw new JobPostingException(environment.getProperty(Constants.COMPANY_NAME_NOT_NULL_AND_EMPTY));
			}
			if (companyRepository.findByCompanyName(jobPostingRequestDto.getCompany()) == null) {
				CompanyRequestDto companyRequestDto = new CompanyRequestDto();
				companyRequestDto.setCompanyName(jobPostingRequestDto.getCompany());
				companyRequestDto.setCompanyLogo(jobPostingRequestDto.getCompanyLogo());
				companyRequestDto.setCompanyUrl(jobPostingRequestDto.getCompanyUrl());
				companyService.addCompanyService(companyRequestDto);
			}
			JobPosting savedJobPosting = jobPostingRepository.save(getPopulatedJob(jobPostingRequestDto));
			logger.info("Job posted successfully");
			return new Response<Object>(savedJobPosting, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_POSTED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addJob of JobPostingServiceImpl : {0}", e);
			logger.error(errorMsg);
			throw new JobPostingException(e.getMessage());
		}

	}

	public JobPosting getPopulatedJob(JobPostingRequestDto jobPostingRequestDto) {
		logger.info("Inside JobPosting of JobPostingServiceImpl");
		JobPosting jobPosting = new JobPosting();
		try {
			logger.info("Inside addJob of JobPostingServiceImpl :{}", jobPostingRequestDto);
			BsonTimestamp bsonTimestamp = new BsonTimestamp(System.currentTimeMillis());
			jobPosting.setCreatedOn(bsonTimestamp);
			jobPosting.setStatus(JobStatus.ACTIVE);
			jobPosting.setCompanyUrl(jobPostingRequestDto.getCompanyUrl());
			jobPosting.setCompany(jobPostingRequestDto.getCompany());
			jobPosting.setDescription(jobPostingRequestDto.getDescription());
			jobPosting.setEmployerEmail(jobPostingRequestDto.getEmployerEmail());
			if (jobPostingRequestDto.getExpectedDateOfJoining() != null) {
				logger.info("Setting expected date of joining");
				jobPosting.setExpectedDateOfJoining(new Date(jobPostingRequestDto.getExpectedDateOfJoining()));
			}
			jobPosting.setExperiencedRequired(jobPostingRequestDto.getExperiencedRequired());
			jobPosting.setExpiredDate(jobPostingRequestDto.getExpiredDate() != null
					? new BsonTimestamp(jobPostingRequestDto.getExpiredDate())
					: null);
			jobPosting.setJobPackage(jobPostingRequestDto.getJobPackage());
			jobPosting.setLocation(jobPostingRequestDto.getLocation());
			jobPosting.setNoOfEmployeesRequired(jobPostingRequestDto.getNoOfEmployeesRequired());
			jobPosting.setPosition(jobPostingRequestDto.getPosition());
			jobPosting.setSkillSet(jobPostingRequestDto.getSkillSet());
			if (jobPostingRequestDto.getCompanyLogo() != null) {
				jobPosting.setCompanyLogo(
						new Binary(BsonBinarySubType.BINARY, jobPostingRequestDto.getCompanyLogo().getBytes()));
			}
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getPopulatedJob of JobPostingServiceImpl : {0}",
					e);
			logger.error(errorMsg);
			throw new JobPostingException(e.getMessage());
		}
		return jobPosting;
	}

	@Override
	public Object updateJob(JobPostingRequestDto jobPostingRequestDto) {
		try {
			logger.info("Inside updateJob of JobPostingServiceImpl");
			if (StringUtils.isEmpty(jobPostingRequestDto.getId())) {
				throw new JobPostingException(environment.getProperty(Constants.INVALID_DATA));
			}
			JobPosting jobPosting = jobPostingRepository.findByIdAndStatus(jobPostingRequestDto.getId(),
					JobStatus.ACTIVE);
			if (jobPosting == null) {
				logger.info("No Job Posting  Found");
				throw new JobPostingException(environment.getProperty(Constants.NO_JOB_POSTING_FOUND));
			}
			JobPosting updatedJobPosting = jobPostingRepository
					.save(getPopulatedJobPostingForUpdate(jobPosting, jobPostingRequestDto));
			logger.info("Job posting updated successfully :{}", updatedJobPosting);
			return new Response<Object>(updatedJobPosting, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_POSTING_UPDATED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in updateJob of JobPostingServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobPostingException(e.getMessage());
		}
	}

	public JobPosting getPopulatedJobPostingForUpdate(JobPosting jobPosting,
			JobPostingRequestDto jobPostingRequestDto) {
		try {
			logger.info("Inside getPopulatedJobPostingForUpdate of JobPostingServiceImpl");
			jobPosting.setId(jobPostingRequestDto.getId());
			jobPosting.setCompanyUrl(jobPostingRequestDto.getCompanyUrl() != null ? jobPostingRequestDto.getCompanyUrl()
					: jobPosting.getCompanyUrl());
			jobPosting.setCompany(jobPostingRequestDto.getCompany() != null ? jobPostingRequestDto.getCompany()
					: jobPosting.getCompany());
			jobPosting.setDescription(
					jobPostingRequestDto.getDescription() != null ? jobPostingRequestDto.getDescription()
							: jobPosting.getDescription());
			if (jobPostingRequestDto.getExpectedDateOfJoining() != null) {
				logger.info("Setting expected date of joining");
				jobPosting.setExpectedDateOfJoining(new Date(jobPostingRequestDto.getExpectedDateOfJoining()));
			} else {
				jobPosting.setExpectedDateOfJoining(jobPosting.getExpectedDateOfJoining());

			}
			jobPosting.setExperiencedRequired(jobPostingRequestDto.getExperiencedRequired() != null
					? jobPostingRequestDto.getExperiencedRequired()
					: jobPosting.getExperiencedRequired());
			jobPosting.setExpiredDate(jobPostingRequestDto.getExpiredDate() != null
					? new BsonTimestamp(jobPostingRequestDto.getExpiredDate())
					: jobPosting.getExpiredDate());
			jobPosting.setJobPackage(jobPostingRequestDto.getJobPackage() != null ? jobPostingRequestDto.getJobPackage()
					: jobPosting.getJobPackage());
			jobPosting.setLocation(jobPostingRequestDto.getLocation() != null ? jobPostingRequestDto.getLocation()
					: jobPosting.getLocation());
			jobPosting.setNoOfEmployeesRequired(jobPostingRequestDto.getNoOfEmployeesRequired() != null
					? jobPostingRequestDto.getNoOfEmployeesRequired()
					: jobPosting.getNoOfEmployeesRequired());
			jobPosting.setPosition(jobPostingRequestDto.getPosition() != null ? jobPostingRequestDto.getPosition()
					: jobPosting.getPosition());
			jobPosting.setSkillSet(jobPostingRequestDto.getSkillSet() != null ? jobPostingRequestDto.getSkillSet()
					: jobPosting.getSkillSet());
			jobPosting.setModifiedOn(new BsonTimestamp(System.currentTimeMillis()));
			jobPosting.setEmployerEmail(
					jobPostingRequestDto.getEmployerEmail() != null ? jobPostingRequestDto.getEmployerEmail()
							: jobPosting.getEmployerEmail());
			if (jobPostingRequestDto.getCompanyLogo() != null) {
				jobPosting.setCompanyLogo(
						new Binary(BsonBinarySubType.BINARY, jobPostingRequestDto.getCompanyLogo().getBytes()));
			} else {
				jobPosting.setCompanyLogo(jobPosting.getCompanyLogo());
			}
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getPopulatedJobPostingForUpdate of JobPostingServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobPostingException(e.getMessage());

		}
		return jobPosting;
	}

	@Override
	public Object getJobPostingByEmployerEmail(String employerEmail, Pageable pageable) {
		try {
			logger.info("Inside getJobPostingByEmployerEmail of JobApplicationServiceImpl");
			if (StringUtils.isEmpty(employerEmail)) {
				logger.info("Invalid data");
				throw new JobPostingException(environment.getProperty(Constants.INVALID_DATA));
			}
			List<JobPostingResponseDto> jobPostingList = new ArrayList<>();
			Page<JobPosting> dataFromRepo = jobPostingRepository.findByEmployerEmail(employerEmail, pageable);
			if (dataFromRepo == null || dataFromRepo.isEmpty()) {
				logger.info("No Job Posting found");
				throw new JobPostingException(environment.getProperty(Constants.NO_JOB_POSTING_FOUND));
			}
			dataFromRepo.forEach(jobPosting -> jobPostingList.add(populateJobPostingResponse(jobPosting)));
			PaginationResponseDto<JobPostingResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(jobPostingList);
			paginationResponseDto.setPageable(dataFromRepo.getPageable());
			paginationResponseDto.setTotalNoOfElements(dataFromRepo.getTotalElements());
			paginationResponseDto.setTotalPages(dataFromRepo.getTotalPages());
			logger.info("Job Posting fetched successfully");
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_POSTING_FETCHED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getJobPostingByEmployerEmail of JobApplicationServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobPostingException(e.getMessage());
		}
	}

	public JobPostingResponseDto populateJobPostingResponse(JobPosting jobPosting) {
		logger.info("Inside populateJobPostingResponse of JobApplicationServiceImpl");
		JobPostingResponseDto jobPostingResponseDto = new JobPostingResponseDto();
		try {
			jobPostingResponseDto.setCompany(jobPosting.getCompany());
			jobPostingResponseDto.setCompanyUrl(jobPosting.getCompanyUrl());
			jobPostingResponseDto
					.setCreatedOn(jobPosting.getCreatedOn() != null ? jobPosting.getCreatedOn().getValue() : null);
			jobPostingResponseDto.setDescription(jobPosting.getDescription());
			jobPostingResponseDto.setEmployerEmail(jobPosting.getEmployerEmail());
			jobPostingResponseDto.setExpectedDateOfJoining(
					jobPosting.getExpectedDateOfJoining() != null ? jobPosting.getExpectedDateOfJoining().getTime()
							: null);
			jobPostingResponseDto.setExperiencedRequired(jobPosting.getExperiencedRequired());
			jobPostingResponseDto.setExpiredDate(
					jobPosting.getExpiredDate() != null ? jobPosting.getExpiredDate().getValue() : null);
			jobPostingResponseDto.setId(jobPosting.getId());
			jobPostingResponseDto.setJobPackage(jobPosting.getJobPackage());
			jobPostingResponseDto.setLocation(jobPosting.getLocation());
			jobPostingResponseDto
					.setModifiedOn(jobPosting.getModifiedOn() != null ? jobPosting.getModifiedOn().getValue() : null);
			jobPostingResponseDto.setNoOfEmployeesRequired(jobPosting.getNoOfEmployeesRequired());
			jobPostingResponseDto.setPosition(jobPosting.getPosition());
			jobPostingResponseDto.setSkillSet(jobPosting.getSkillSet());
			jobPostingResponseDto.setStatus(jobPosting.getStatus());
			jobPostingResponseDto.setCompanyLogo(jobPosting.getCompanyLogo());
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in populateJobPostingResponse of JobPostingServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobPostingException(e.getMessage());
		}
		return jobPostingResponseDto;
	}

	@Override
	public Object getAllJobPostings(String searchParam, Pageable pageable) {
		try {
			logger.info("Inside getAllJobPostings of JobPostingServiceImpl");
			List<JobPostingResponseDto> jobPostingList = new ArrayList<>();
			Page<JobPosting> dataFromRepo = jobPostingRepository
					.findByLocationLikeOrStatusLikeOrCompanyLikeOrderByCompany(searchParam, searchParam, searchParam,
							pageable);
			if (dataFromRepo == null || dataFromRepo.isEmpty()) {
				logger.info("No Job Posting found");
				throw new JobPostingException(environment.getProperty(Constants.NO_JOB_POSTING_FOUND));
			}
			dataFromRepo.forEach(jobPosting -> jobPostingList.add(populateJobPostingResponse(jobPosting)));
			PaginationResponseDto<JobPostingResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(jobPostingList);
			paginationResponseDto.setPageable(dataFromRepo.getPageable());
			paginationResponseDto.setTotalNoOfElements(dataFromRepo.getTotalElements());
			paginationResponseDto.setTotalPages(dataFromRepo.getTotalPages());
			logger.info("Job Posting fetched successfully");
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.JOB_POSTING_FETCHED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getAllJobPostings of JobPostingServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new JobPostingException(e.getMessage());
		}
	}

}
