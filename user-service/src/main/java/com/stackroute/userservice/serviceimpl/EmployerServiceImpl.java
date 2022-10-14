package com.stackroute.userservice.serviceimpl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.stackroute.userservice.dto.EmployerRequestDto;
import com.stackroute.userservice.dto.EmployerResponseDto;
import com.stackroute.userservice.dto.PaginationResponseDto;
import com.stackroute.userservice.dto.SeekerDto;
import com.stackroute.userservice.enums.EmployerStatus;
import com.stackroute.userservice.enums.SeekerStatus;
import com.stackroute.userservice.exception.EmployerException;
import com.stackroute.userservice.exception.SeekerException;
import com.stackroute.userservice.modal.Employer;
import com.stackroute.userservice.modal.Seeker;
import com.stackroute.userservice.rabbitmq.Producer;
import com.stackroute.userservice.rabbitmq.UserDTO;
import com.stackroute.userservice.repository.EmployerRepository;
import com.stackroute.userservice.repository.SeekerRepository;
import com.stackroute.userservice.service.EmployerService;
import com.stackroute.userservice.utility.Constants;
import com.stackroute.userservice.utility.Response;

@Service
public class EmployerServiceImpl implements EmployerService {

	Logger logger = LoggerFactory.getLogger(EmployerServiceImpl.class);

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private Environment environment;

	@Autowired
	Producer producer;

	@Autowired
	SeekerRepository seekerRepository;

	@Override
	public Object addEmployer(EmployerRequestDto employerRequestDto) {
		try {
			logger.info("Inside addEmployer of EmployerServiceImpl");
			validateEmployerRequest(employerRequestDto);
			Employer savedEmployer = employerRepository.save(getPopulatedEmployer(employerRequestDto));
			sendMsgToRabbitMq(employerRequestDto);
			return new Response<Object>(savedEmployer, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.EMPLOYER_SAVED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addEmployer of EmployerServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new EmployerException(e.getMessage());
		}
	}

	public void sendMsgToRabbitMq(EmployerRequestDto employerRequestDto) {
		try {
			logger.info("Inside sendMsgToRabbitMq of EmployerServiceImpl");
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(employerRequestDto.getEmail());
			userDTO.setPassword(employerRequestDto.getPassword());
			userDTO.setUserType(Constants.USER_TYPE_EMPLOYER);
			producer.sendMessageToRabbitMq(userDTO);
			logger.info("Message sent to rabbit mq : userDto is :{}", userDTO);
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in sendMsgToRabbitMq of SeekerServiceImpl : {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			// throw new SeekerException(e.getMessage());
		}
	}

	public void validateEmployerRequest(EmployerRequestDto employerRequestDto) {
		try {
			logger.info("Inside validateEmployerRequest of EmployerController");
			if (StringUtils.isEmpty(employerRequestDto.getEmail()) || StringUtils.isEmpty(employerRequestDto.getEmail())
					|| StringUtils.isEmpty(employerRequestDto.getPassword())) {
				logger.info("Getting invalid data");
				throw new EmployerException(environment.getProperty(Constants.INVALID_DATA));
			}
			Object employer = employerRepository.findByEmailAndStatus(employerRequestDto.getEmail(),
					EmployerStatus.ACTIVE);
			if (employer != null) {
				logger.info("Employer already exist");
				throw new EmployerException(environment.getProperty(Constants.EMPLOYER_EXIST));
			}
			Seeker seeker = seekerRepository.findByEmailAndStatus(employerRequestDto.getEmail(), SeekerStatus.ACTIVE);
			if (seeker != null) {
				throw new EmployerException(environment.getProperty(Constants.ALREADY_REGISTER_AS_AN_SEEKER));
			}

		} catch (Exception e) {
			throw new EmployerException(e.getMessage());
		}
	}

	public Employer getPopulatedEmployer(EmployerRequestDto employerRequestDto) {
		logger.info("Inside getPopulatedEmployer of EmployerController");
		Employer employer = new Employer();
		employer.setName(employerRequestDto.getName());
		try {
			if (employerRequestDto.getProfilePhoto() != null) {
				employer.setProfilePhoto(
						new Binary(BsonBinarySubType.BINARY, employerRequestDto.getProfilePhoto().getBytes()));
			}
		} catch (Exception e) {
			logger.error("Profile photo is not in proper format");
			throw new EmployerException(environment.getProperty(Constants.EMPLOYER_PROFILE_PHOTO_NOT_PROPER_FORMAT));
		}
		employer.setCompany(employerRequestDto.getCompany() != null ? employerRequestDto.getCompany() : null);
		employer.setCompanyUrl(employerRequestDto.getCompanyUrl() != null ? employerRequestDto.getCompanyUrl() : null);
		employer.setEmail(employerRequestDto.getEmail());
		employer.setMobileNumber(
				employerRequestDto.getMobileNumber() != null ? employerRequestDto.getMobileNumber() : null);
		employer.setName(employerRequestDto.getName() != null ? employerRequestDto.getName() : null);
		employer.setPassword(employerRequestDto.getPassword());
		employer.setStatus(EmployerStatus.ACTIVE);
		employer.setYearsOfExperience(
				employerRequestDto.getYearsOfExperience() != null ? employerRequestDto.getYearsOfExperience() : null);
		return employer;
	}

	@Override
	public Object getEmployerByEmail(String email) {
		logger.info("Inside getEmployerByEmail of EmployerService");
		EmployerResponseDto employerResponseDto = null;
		try {
			if (StringUtils.isEmpty(email)) {
				logger.info("Email cannot be null or empty");
				throw new EmployerException(environment.getProperty(Constants.INVALID_DATA));
			}
			Employer employer = employerRepository.findByEmailAndStatus(email, EmployerStatus.ACTIVE);
			if (employer == null) {
				throw new EmployerException(environment.getProperty(Constants.NO_EMPLOYER_FOUND));
			}
			employerResponseDto = populateEmployerResponse(employer);
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in getEmployerByEmail of EmployerServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new EmployerException(e.getMessage());
		}
		return new Response<Object>(employerResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.EMPLOYER_FETCHED_SUCCESSFULLY));
	}

	public EmployerResponseDto populateEmployerResponse(Employer employer) {
		logger.info("Inside populateEmployerResponse of EmployerSericeImpl");
		EmployerResponseDto employerResponseDto = new EmployerResponseDto();
		try {
			employerResponseDto.set_id(employer.get_id());
			employerResponseDto.setCompany(employer.getCompany());
			employerResponseDto.setCompanyUrl(employer.getCompanyUrl());
			employerResponseDto.setEmail(employer.getEmail());
			employerResponseDto.setMobileNumber(employer.getMobileNumber());
			employerResponseDto.setName(employer.getName());
			employerResponseDto.setPassword(employer.getPassword());
			employerResponseDto.setProfilePhoto(employer.getProfilePhoto());
			employerResponseDto.setStatus(employer.getStatus());
			employerResponseDto.setYearsOfExperience(employer.getYearsOfExperience());
		} catch (Exception e) {
			logger.error("Exception caught in populateEmployerResponse of EmployerServiceImpl");
			throw new EmployerException(e.getMessage());
		}
		return employerResponseDto;
	}

	@Override
	public Object updateEmployer(EmployerRequestDto employerRequestDto) {
		try {
			logger.info("Inside updateEmployer of EmployerServiceImpl");
			if (StringUtils.isEmpty(employerRequestDto.getEmail())) {
				throw new EmployerException(environment.getProperty(Constants.INVALID_DATA));
			}
			Employer employerFromrepo = employerRepository.findByEmailAndStatus(employerRequestDto.getEmail(),
					EmployerStatus.ACTIVE);
			if (employerFromrepo == null) {
				throw new EmployerException(environment.getProperty(Constants.NO_EMPLOYER_FOUND));
			}
			Employer savedEmployer = employerRepository
					.save(getPopulatedEmployerForUpdate(employerFromrepo, employerRequestDto));
			sendMsgToRabbitMq(employerRequestDto);
			return new Response<Object>(savedEmployer, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.EMPLOYER_UPDATED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in updateEmployer of EmployerServiceImpl : {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new EmployerException(e.getMessage());
		}
	}

	public Employer getPopulatedEmployerForUpdate(Employer employer, EmployerRequestDto employerRequestDto) {
		logger.info("Inside getPopulatedEmployerForUpdate of EmployerController with employer : {}", employer);
		try {
			employer.setName(employer.getName() != null ? employerRequestDto.getName() : employer.getName());
			employer.set_id(employer.get_id());
			if (employer.getProfilePhoto() != null) {
				employer.setProfilePhoto(
						new Binary(BsonBinarySubType.BINARY, employerRequestDto.getProfilePhoto().getBytes()));
			}
			employer.setCompany(
					employerRequestDto.getCompany() != null ? employerRequestDto.getCompany() : employer.getCompany());
			employer.setCompanyUrl(employerRequestDto.getCompanyUrl() != null ? employerRequestDto.getCompanyUrl()
					: employer.getCompany());
			employer.setEmail(
					employerRequestDto.getEmail() != null ? employerRequestDto.getEmail() : employer.getEmail());
			employer.setMobileNumber(employerRequestDto.getMobileNumber() != null ? employerRequestDto.getMobileNumber()
					: employer.getMobileNumber());
			employer.setPassword(employerRequestDto.getPassword() != null ? employerRequestDto.getPassword()
					: employer.getPassword());
			employer.setYearsOfExperience(
					employerRequestDto.getYearsOfExperience() != null ? employerRequestDto.getYearsOfExperience()
							: employer.getYearsOfExperience());
		} catch (Exception e) {
			logger.error("Profile photo are not in proper format");
			throw new EmployerException(environment.getProperty(Constants.EMPLOYER_PROFILE_PHOTO_NOT_PROPER_FORMAT));
		}
		return employer;
	}

	@Override
	public Object getAllEmployers(Pageable pageable, String searchParam) {
		try {
			List<EmployerResponseDto> employerList = new ArrayList<>();
			Page<Employer> dataFromRepo = employerRepository.findByNameLikeOrCompanyLikeOrderByName(searchParam,
					searchParam, pageable);
			if (dataFromRepo == null || dataFromRepo.isEmpty()) {
				logger.info("No Employer found");
				throw new EmployerException(environment.getProperty(Constants.NO_EMPLOYER_FOUND));
			}
			dataFromRepo.forEach(employer -> employerList.add(populateEmployerResponse(employer)));
			PaginationResponseDto<EmployerResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(employerList);
			paginationResponseDto.setPageable(dataFromRepo.getPageable());
			paginationResponseDto.setTotalNoOfElements(dataFromRepo.getTotalElements());
			paginationResponseDto.setTotalPages(dataFromRepo.getTotalPages());
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.EMPLOYER_FETCHED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getAllEmployers of EmployerServiceImpl : {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new EmployerException(e.getMessage());
		}
	}

}
