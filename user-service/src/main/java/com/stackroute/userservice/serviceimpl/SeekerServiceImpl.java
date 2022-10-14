package com.stackroute.userservice.serviceimpl;

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

import com.stackroute.userservice.dto.PaginationResponseDto;
import com.stackroute.userservice.dto.SeekerDto;
import com.stackroute.userservice.dto.SeekerResponseDto;
import com.stackroute.userservice.enums.EmployerStatus;
import com.stackroute.userservice.enums.SeekerStatus;
import com.stackroute.userservice.exception.SeekerException;
import com.stackroute.userservice.modal.Employer;
import com.stackroute.userservice.modal.Seeker;
import com.stackroute.userservice.rabbitmq.Producer;
import com.stackroute.userservice.rabbitmq.UserDTO;
import com.stackroute.userservice.repository.EmployerRepository;
import com.stackroute.userservice.repository.SeekerRepository;
import com.stackroute.userservice.service.SeekerService;
import com.stackroute.userservice.utility.Constants;
import com.stackroute.userservice.utility.Response;

@Service
public class SeekerServiceImpl implements SeekerService {

	Logger logger = LoggerFactory.getLogger(SeekerServiceImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private SeekerRepository seekerRepository;

	@Autowired
	Producer producer;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	

	@Override
	public Object addSeeker(SeekerDto seekerDto) {
		try {
			logger.info("Inside addSeeker of SeekerServiceImpl");
			validateSeekerRequest(seekerDto);
			Seeker savedSeeker = seekerRepository.save(getPopulatedSeeker(seekerDto));
			logger.info("Seeker saved successfully : {}", savedSeeker);
			sendMsgToRabbitMq(seekerDto);
			return new Response<Object>(savedSeeker, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.SEEKER_SAVED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addSeeker of SeekerServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new SeekerException(e.getMessage());
		}
	}

	public void sendMsgToRabbitMq(SeekerDto seekerDto) {
		try {
			logger.info("Inside sendMsgToRabbitMq of SeekerServiceImpl");
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(seekerDto.getEmail());
			userDTO.setPassword(seekerDto.getPassword());
			userDTO.setUserType(Constants.USER_TYPE_SEEKER);
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

	public void validateSeekerRequest(SeekerDto seekerDto) {
		try {
			logger.info("Inside validateSeekerRequest of SeekerController");
			if (StringUtils.isEmpty(seekerDto.getEmail()) || StringUtils.isEmpty(seekerDto.getEmail())
					|| StringUtils.isEmpty(seekerDto.getPassword())) {
				logger.info("Getting invalid data");
				throw new SeekerException(environment.getProperty(Constants.INVALID_DATA));
			}
			Object seeker = seekerRepository.findByEmailAndStatus(seekerDto.getEmail(), SeekerStatus.ACTIVE);
			if (seeker != null) {
				logger.info("Seeker already exist");
				throw new SeekerException(environment.getProperty(Constants.SEEKER_EXIST));
			}

			Employer employer = employerRepository.findByEmailAndStatus(seekerDto.getEmail(), EmployerStatus.ACTIVE);
			if (employer != null) {
				logger.info("Seeker already exist");
				throw new SeekerException(environment.getProperty(Constants.ALREADY_REGISTER_AS_AN_EMPLOYEE));
			}
		} catch (Exception e) {
			throw new SeekerException(e.getMessage());
		}
	}

	public Seeker getPopulatedSeeker(SeekerDto seekerDto) {
		logger.info("Inside getPopulatedSeeker of SeekerController");
		Seeker seeker = new Seeker();
		seeker.setName(seekerDto.getName());
		try {
			if (seekerDto.getProfilePhoto() != null) {
				seeker.setProfilePhoto(new Binary(BsonBinarySubType.BINARY, seekerDto.getProfilePhoto().getBytes()));
			}
			if (seekerDto.getResume() != null) {
				seeker.setResume(new Binary(BsonBinarySubType.BINARY, seekerDto.getResume().getBytes()));
			}
		} catch (Exception e) {
			logger.error("Resume or Profile are not in proper format");
			throw new SeekerException(environment.getProperty(Constants.RESUME_PROFILE_PHOTO_NOT_PROPER_FORMAT));
		}
		seeker.setCurrentOrganisation(
				seekerDto.getCurrentOrganisation() != null ? seekerDto.getCurrentOrganisation() : null);
		seeker.setCurrentRole(seekerDto.getCurrentRole() != null ? seekerDto.getCurrentRole() : null);
		seeker.setDescription(seekerDto.getDescription() != null ? seekerDto.getDescription() : null);
		if (seekerDto.getDob() != null) {
			seeker.setDob(new Date(seekerDto.getDob()));
		}
		seeker.setDomain(seekerDto.getDomain() != null ? seekerDto.getDomain() : null);
		seeker.setEducation(seekerDto.getEducation() != null ? seekerDto.getEducation() : null);
		seeker.setEmail(seekerDto.getEmail());
		seeker.setExperience(seekerDto.getExperience() != null ? seekerDto.getExperience() : null);
		seeker.setLocation(seekerDto.getLocation() != null ? seekerDto.getLocation() : null);
		seeker.setSkillSet(seekerDto.getSkillSet() != null ? seekerDto.getSkillSet() : null);
		seeker.setHigherEducation(seekerDto.getHigherEducation() != null ? seekerDto.getHigherEducation() : null);
		seeker.setMobileNumber(seekerDto.getMobileNumber() != null ? seekerDto.getMobileNumber() : null);
		seeker.setName(seekerDto.getName() != null ? seekerDto.getName() : null);
		seeker.setNoticePeriod(seekerDto.getNoticePeriod() != null ? seekerDto.getNoticePeriod() : null);
		seeker.setDomain(seekerDto.getDomain());
		seeker.setStatus(SeekerStatus.ACTIVE);
		seeker.setPassword(seekerDto.getPassword());
		BsonTimestamp bsonTimestamp = new BsonTimestamp(System.currentTimeMillis());
		seeker.setCreatedOn(bsonTimestamp);
		logger.info("Populated seeker is :{}", seeker);
		return seeker;
	}

	@Override
	public Object getAllSeeker(Pageable pageable, String searchParam) {
		try {
			logger.info("Inside getAllSeeker of SeekerServiceImpl");
			List<SeekerResponseDto> seekersList = new ArrayList<>();
			Page<Seeker> dataFromRepo = seekerRepository
					.findByNameLikeOrLocationLikeOrNoticePeriodLikeOrDomainLikeOrderByName(searchParam, searchParam,
							searchParam, searchParam, pageable);
			if (dataFromRepo == null || dataFromRepo.isEmpty()) {
				logger.info("No seeker found");
				throw new SeekerException(environment.getProperty(Constants.NO_SEEKER_FOUND));
			}
			dataFromRepo.forEach(seeker -> seekersList.add(populateSeekerResponse(seeker)));
			PaginationResponseDto<SeekerResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(seekersList);
			paginationResponseDto.setPageable(dataFromRepo.getPageable());
			paginationResponseDto.setTotalNoOfElements(dataFromRepo.getTotalElements());
			paginationResponseDto.setTotalPages(dataFromRepo.getTotalPages());
			logger.info("Seeker fetched successfully");
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.SEEKERS_FETCHED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getAllSeeker of SeekerServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new SeekerException(e.getMessage());
		}
	}

	@Override
	public Object getSeekerByEmail(String email) {
		logger.info("Inside getSeekerByEmail of SeekerSericeImpl");
		SeekerResponseDto seekerResponseDto = null;
		try {
			if (StringUtils.isEmpty(email)) {
				logger.info("Email cannot be null or empty");
				throw new SeekerException(environment.getProperty(Constants.INVALID_DATA));
			}
			Seeker seeker = seekerRepository.findByEmailAndStatus(email, SeekerStatus.ACTIVE);
			if (seeker == null) {
				logger.info("Seeker Not found");
				throw new SeekerException(environment.getProperty(Constants.NO_SEEKER_FOUND));
			}
			seekerResponseDto = populateSeekerResponse(seeker);
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getSeekerByEmail of SeekerServiceImpl : {0}",
					e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new SeekerException(e.getMessage());
		}
		logger.info("Populated dto email is :{}", seekerResponseDto.getEmail());
		return new Response<Object>(seekerResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.SEEKERS_FETCHED_SUCCESSFULLY));
	}

	public SeekerResponseDto populateSeekerResponse(Seeker seeker) {
		logger.info("Inside populateSeekerResponse of SeekerServiceImpl with seeker :{}", seeker);
		SeekerResponseDto seekerResponseDto = new SeekerResponseDto();
		try {
			seekerResponseDto.setCreatedOn(seeker.getCreatedOn() != null ? seeker.getCreatedOn().getValue() : null);
			seekerResponseDto.setCurrentOrganisation(seeker.getCurrentOrganisation());
			seekerResponseDto.setCurrentRole(seeker.getCurrentRole());
			seekerResponseDto.setDescription(seeker.getDescription());
			seekerResponseDto.setDob(seeker.getDob() != null ? seeker.getDob().getTime() : null);
			seekerResponseDto.setDomain(seeker.getDomain());
			seekerResponseDto.setEducation(seeker.getEducation());
			seekerResponseDto.setEmail(seeker.getEmail());
			seekerResponseDto.setExperience(seeker.getExperience());
			seekerResponseDto.setHigherEducation(seeker.getHigherEducation());
			seekerResponseDto.setLocation(seeker.getLocation());
			seekerResponseDto.setMobileNumber(seeker.getMobileNumber());
			seekerResponseDto.setModifiedOn(seeker.getModifiedOn() != null ? seeker.getModifiedOn().getValue() : null);
			seekerResponseDto.setName(seeker.getName());
			seekerResponseDto.setNoticePeriod(seeker.getNoticePeriod());
			seekerResponseDto.setPassword(seeker.getPassword());
			seekerResponseDto.setProfilePhoto(seeker.getProfilePhoto());
			seekerResponseDto.setSkillSet(seeker.getSkillSet());
			seekerResponseDto.setStatus(seeker.getStatus());
			seekerResponseDto.setResume(seeker.getResume());
		} catch (Exception e) {
			logger.error("Exception caught in populateSeekerResponse of SeekerServiceImpl");
			throw new SeekerException(e.getMessage());
		}
		logger.info("Populated seekerResponseDto is :{}", seekerResponseDto.getEmail());
		return seekerResponseDto;
	}

	@Override
	public Object updateSeeker(SeekerDto seekerDto) {
		try {
			logger.info("Inside updateSeeker of SeekerServiceImpl :{}", seekerDto);
			if (StringUtils.isEmpty(seekerDto.getEmail())) {
				throw new SeekerException(environment.getProperty(Constants.INVALID_DATA));
			}
			Seeker seekerFromrepo = seekerRepository.findByEmailAndStatus(seekerDto.getEmail(), SeekerStatus.ACTIVE);
			if (seekerFromrepo == null) {
				logger.info("No seeker Found");
				throw new SeekerException(environment.getProperty(Constants.NO_SEEKER_FOUND));
			}
			Seeker savedSeeker = seekerRepository.save(getPopulatedSeekerForUpdate(seekerFromrepo, seekerDto));
			sendMsgToRabbitMq(seekerDto);
			logger.info("Seeker updated successfully :{}", savedSeeker);
			return new Response<Object>(savedSeeker, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.SEEKER_UPDATED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in updateSeeker of SeekerServiceImpl : {0}", e);
			logger.error(errorMsg);
			e.printStackTrace();
			throw new SeekerException(e.getMessage());
		}
	}

	public Seeker getPopulatedSeekerForUpdate(Seeker seeker, SeekerDto seekerDto) {
		logger.info("Inside getPopulatedSeekerForUpdate of SeekerController with seeker : {}", seeker);
		seeker.setName(seekerDto.getName() != null ? seekerDto.getName() : seeker.getName());
		seeker.set_id(seeker.get_id());
		try {
			if (seekerDto.getProfilePhoto() != null) {
				seeker.setProfilePhoto(new Binary(BsonBinarySubType.BINARY, seekerDto.getProfilePhoto().getBytes()));
			}
			if (seekerDto.getResume() != null) {
				seeker.setResume(new Binary(BsonBinarySubType.BINARY, seekerDto.getResume().getBytes()));
			}
		} catch (Exception e) {
			logger.error("Resume or Profile are not in proper format");
			throw new SeekerException(environment.getProperty(Constants.RESUME_PROFILE_PHOTO_NOT_PROPER_FORMAT));
		}
		seeker.setCurrentOrganisation(seekerDto.getCurrentOrganisation() != null ? seekerDto.getCurrentOrganisation()
				: seeker.getCurrentOrganisation());
		seeker.setCurrentRole(
				seekerDto.getCurrentRole() != null ? seekerDto.getCurrentRole() : seeker.getCurrentRole());
		seeker.setDescription(
				seekerDto.getDescription() != null ? seekerDto.getDescription() : seeker.getDescription());
		if (seekerDto.getDob() != null) {
			seeker.setDob(new Date(seekerDto.getDob()));
		}
		seeker.setDomain(seekerDto.getDomain() != null ? seekerDto.getDomain() : seeker.getDomain());
		seeker.setEducation(seekerDto.getEducation() != null ? seekerDto.getEducation() : seeker.getEducation());
		seeker.setEmail(seekerDto.getEmail() != null ? seekerDto.getEmail() : seeker.getEmail());
		seeker.setExperience(seekerDto.getExperience() != null ? seekerDto.getExperience() : seeker.getExperience());
		seeker.setLocation(seekerDto.getLocation() != null ? seekerDto.getLocation() : seeker.getLocation());
		seeker.setSkillSet(seekerDto.getSkillSet() != null ? seekerDto.getSkillSet() : seeker.getSkillSet());
		seeker.setHigherEducation(
				seekerDto.getHigherEducation() != null ? seekerDto.getHigherEducation() : seeker.getHigherEducation());
		seeker.setMobileNumber(
				seekerDto.getMobileNumber() != null ? seekerDto.getMobileNumber() : seeker.getMobileNumber());
		seeker.setName(seekerDto.getName() != null ? seekerDto.getName() : seeker.getName());
		seeker.setNoticePeriod(
				seekerDto.getNoticePeriod() != null ? seekerDto.getNoticePeriod() : seeker.getNoticePeriod());
		seeker.setDomain(seekerDto.getDomain());
		seeker.setStatus(SeekerStatus.ACTIVE);
		seeker.setPassword(seekerDto.getPassword());
		BsonTimestamp bsonTimestamp = new BsonTimestamp(System.currentTimeMillis());
		seeker.setModifiedOn(bsonTimestamp);
		logger.info("Populated seeker is :{}", seeker);
		return seeker;
	}
}