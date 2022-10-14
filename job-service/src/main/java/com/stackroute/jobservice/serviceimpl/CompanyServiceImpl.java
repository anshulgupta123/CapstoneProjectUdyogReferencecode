package com.stackroute.jobservice.serviceimpl;

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

import com.stackroute.jobservice.dto.CompanyRequestDto;
import com.stackroute.jobservice.dto.CompanyResponseDto;
import com.stackroute.jobservice.dto.PaginationResponseDto;
import com.stackroute.jobservice.exception.CompanyException;
import com.stackroute.jobservice.modal.Company;
import com.stackroute.jobservice.repository.CompanyRepository;
import com.stackroute.jobservice.service.CompanyService;
import com.stackroute.jobservice.utility.Constants;
import com.stackroute.jobservice.utility.Response;

@Service
public class CompanyServiceImpl implements CompanyService {

	Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	Environment environment;

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Object addCompanyService(CompanyRequestDto companyRequestDto) {
		try {
			logger.info("Inside addCompanyService of CompanyServiceImpl");
			Company company = new Company();
			company.setCompanyName(companyRequestDto.getCompanyName());
			company.setCompanyUrl(companyRequestDto.getCompanyUrl());
			if (companyRequestDto.getCompanyLogo() != null) {
				company.setCompanyLogo(
						new Binary(BsonBinarySubType.BINARY, companyRequestDto.getCompanyLogo().getBytes()));
			}
			Company savedCompany = companyRepository.save(company);
			logger.info("Company saved successfully");
			return new Response<Object>(savedCompany, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.COMPANY_SAVED_SUCCESSFULLY));
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in addCompanyService of CompanyServiceImpl :{0}",
					e);
			logger.error(errorMsg);
			throw new CompanyException(e.getMessage());
		}
	}

	@Override
	public Object getCompanyByName(String companyName) {
		try {
			logger.info("Inside getCompanyByName of CompanyServiceImpl");
			Company companyFromRepo = companyRepository.findByCompanyName(companyName);
			if (companyFromRepo == null) {
				throw new CompanyException(environment.getProperty(Constants.NO_COMPANY_FOUND));
			}
			CompanyResponseDto populatedCompany = populateComanyResponseDto(companyFromRepo);
			return new Response<Object>(populatedCompany, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.COMPANY_FETCHED_SUCCESSFULLY));

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getCompanyByName of CompanyServiceImpl :{0}",
					e);
			logger.error(errorMsg);
			throw new CompanyException(e.getMessage());
		}
	}

	public CompanyResponseDto populateComanyResponseDto(Company companyFromRepo) {
		CompanyResponseDto companyResponseDto = new CompanyResponseDto();
		try {
			logger.info("Inside populateComanyResponseDto of CompanyServiceImpl");
			companyResponseDto.setCompanyId(companyFromRepo.get_id());
			companyResponseDto.setCompanyLogo(companyFromRepo.getCompanyLogo());
			companyResponseDto.setCompanyName(companyFromRepo.getCompanyName());
			companyResponseDto.setCompanyUrl(companyFromRepo.getCompanyUrl());
		} catch (Exception e) {
			String errorMsg = MessageFormat
					.format("Exception caught in populateComanyResponseDtos of CompanyServiceImpl :{0}", e);
			logger.error(errorMsg);
			throw new CompanyException(e.getMessage());
		}
		return companyResponseDto;
	}

	@Override
	public Object getAllCompanies(Pageable pageable) {
		try {
			logger.info("Inside getAllCompanies of CompanyServiceImpl");
			List<CompanyResponseDto> listOfCompanyResponseDto = new ArrayList<>();
			Page<Company> companyFromRepo = companyRepository.findAll(pageable);
			if (companyFromRepo.isEmpty()) {
				throw new CompanyException(environment.getProperty(Constants.NO_COMPANY_FOUND));
			}
			companyFromRepo
					.forEach(company -> listOfCompanyResponseDto.add(populateComanyResponseDto(company)));
			PaginationResponseDto<CompanyResponseDto> paginationResponseDto = new PaginationResponseDto<>();
			paginationResponseDto.setPaginatedData(listOfCompanyResponseDto);
			paginationResponseDto.setPageable(companyFromRepo.getPageable());
			logger.info("Companies fetched successfully");
			return new Response<Object>(paginationResponseDto, environment.getProperty(Constants.SUCCESS_CODE),
					environment.getProperty(Constants.COMPANY_FETCHED_SUCCESSFULLY));

		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in getAllCompanies of CompanyServiceImpl :{0}", e);
			logger.error(errorMsg);
			throw new CompanyException(e.getMessage());
		}
	}

}
