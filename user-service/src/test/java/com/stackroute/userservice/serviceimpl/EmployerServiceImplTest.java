package com.stackroute.userservice.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.stackroute.userservice.dto.EmployerRequestDto;
import com.stackroute.userservice.dto.EmployerResponseDto;
import com.stackroute.userservice.dto.PaginationResponseDto;
import com.stackroute.userservice.enums.EmployerStatus;
import com.stackroute.userservice.exception.EmployerException;
import com.stackroute.userservice.modal.Employer;
import com.stackroute.userservice.repository.EmployerRepository;
import com.stackroute.userservice.repository.SeekerRepository;
import com.stackroute.userservice.utility.Response;

@ExtendWith(MockitoExtension.class)
class EmployerServiceImplTest {
	
	Logger logger = LoggerFactory.getLogger(EmployerServiceImplTest.class);
	
	@Mock
	private Environment environment;
	
	@Mock
	private EmployerRepository employerRepository;
	
	
	@Mock
	private SeekerRepository seekerRepository;
	
	@InjectMocks
	EmployerServiceImpl employerServiceImpl;

	@Test
	@DisplayName("Testing get employer by email")
	void testgetEmployerByEmail() {
		Employer employer = new Employer();
		employer.setEmail("e11@gmail.com");
		employer.setPassword("abcd");
		when(employerRepository.findByEmailAndStatus("e11@gmail.com", EmployerStatus.ACTIVE)).thenReturn(employer);
		Response object = (Response) employerServiceImpl.getEmployerByEmail("e11@gmail.com");
		logger.info("RESponse is :{}", object.toString());
		EmployerResponseDto employerResponseDto = (EmployerResponseDto) object.getData();
		assertEquals("e11@gmail.com", employerResponseDto.getEmail());

	}
	
	@Test
	@DisplayName("Testing get employer by email for empty input")
	void testGetEmployerByEmailEmptyInput() {
		assertThrows(EmployerException.class, () -> employerServiceImpl.getEmployerByEmail(""));
	}

	@Test
	@DisplayName("Testing get employer by email when employer is null")
	void testGetEmployerByEmailEmployerIsNull() {
		when(employerRepository.findByEmailAndStatus("e345@gmail.com", EmployerStatus.ACTIVE)).thenReturn(null);
		assertThrows(EmployerException.class, () -> employerServiceImpl.getEmployerByEmail("e345@gmail.com"));
	}
	
	@Test
	@DisplayName("Testing get All Employer when there is no data from repo")
	void testGetAllEmployerNoEmployerFound() {
		Pageable pageable = PageRequest.of(0, 40);
		when(employerRepository.findByNameLikeOrCompanyLikeOrderByName("", "", 
				pageable)).thenReturn(null);
		assertThrows(EmployerException.class, () -> employerServiceImpl.getAllEmployers(pageable, ""));

	}

	@Test
	@DisplayName("Testing get All Employer Data and Pagination Object Both EXist Or Not")
	void testGetAllEmployer() {
		Employer employer = new Employer();
		employer.setEmail("e5001@gmail.com");
		Employer employer2 = new Employer();
		employer2.setEmail("e6001@gmail.com");
		List<Employer> employerList = new ArrayList<>();
		employerList.add(employer);
		employerList.add(employer2);
		Page<Employer> dataFromRepo = new PageImpl<>(employerList);
		Pageable pageable = PageRequest.of(0, 40);
		when(employerRepository.findByNameLikeOrCompanyLikeOrderByName("", "", pageable)).thenReturn(dataFromRepo);
		Response response = (Response) employerServiceImpl.getAllEmployers(pageable, "");
		PaginationResponseDto<EmployerResponseDto> paginationResponseDto = (PaginationResponseDto<EmployerResponseDto>) response
				.getData();
		assertEquals(2, paginationResponseDto.getPaginatedData().size());
		assertNotNull(paginationResponseDto.getPageable());

	}
	
	
	@Test
	@DisplayName("Testing add employer for empty mail")
	void testAddEmployerForEmptyEmail() {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		employerRequestDto.setPassword("12345");
		assertThrows(EmployerException.class, () -> employerServiceImpl.addEmployer(employerRequestDto));
	}

	@Test
	@DisplayName("Tesing add employer for empty password")
	void testaddEmployerForEmptyPassword() {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		employerRequestDto.setEmail("ad2@gmail.com");
		assertThrows(EmployerException.class, () -> employerServiceImpl.addEmployer(employerRequestDto));

	}

	@Test
	@DisplayName("Tesing add employer for empty password and mail")
	void testaddEmployerForEmptyMailAndPassword() {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		assertThrows(EmployerException.class, () -> employerServiceImpl.addEmployer(employerRequestDto));
	}

	@Test
	@DisplayName("Tesing add employer, employer already exist")
	void testaddEmployerEmployerExist() {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		Employer employer = new Employer();
		employer.setEmail("akge@gmail.com");
		when(employerRepository.findByEmailAndStatus("akge@gmail.com", EmployerStatus.ACTIVE)).thenReturn(employer);
		employerRequestDto.setEmail("akge@gmail.com");
		employerRequestDto.setPassword("12345");
		assertThrows(EmployerException.class, () -> employerServiceImpl.addEmployer(employerRequestDto));
	}

	@Test
	@DisplayName("Tesing add employer")
	void testaddEmployer() {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		Employer employer = new Employer();
		employer.setEmail("ak893@gmail.com");
		employer.setPassword("12345");
		when(employerRepository.findByEmailAndStatus("ak893@gmail.com", EmployerStatus.ACTIVE)).thenReturn(null);
		when(employerRepository.save(any(Employer.class))).thenReturn(employer);
		employerRequestDto.setEmail("ak893@gmail.com");
		employerRequestDto.setPassword("12345");
		Response response = (Response) employerServiceImpl.addEmployer(employerRequestDto);
		logger.info("Response is :{}", response);
		assertEquals("ak893@gmail.com", ((Employer) response.getData()).getEmail());
	}

	@Test
	@DisplayName("Testing update employer for empty email")
	void testUpdateEmployerEmptyEmail() {
	EmployerRequestDto	employerRequestDto = new EmployerRequestDto();
		assertThrows(EmployerException.class, () -> employerServiceImpl.updateEmployer(employerRequestDto));
	}

	@Test
	@DisplayName("Testing update employer when does not exist")
	void testUpdateEmployerEmployerDoesNotExist() {
		EmployerRequestDto	employerRequestDto = new EmployerRequestDto();
		employerRequestDto.setEmail("ak8934@gmail.com");
		when(employerRepository.findByEmailAndStatus("ak8934@gmail.com", EmployerStatus.ACTIVE)).thenReturn(null);
		assertThrows(EmployerException.class, () -> employerServiceImpl.updateEmployer(employerRequestDto));
	}

	@Test
	@DisplayName("Testing update employer")
	void testUpdateEmployer() {
		EmployerRequestDto	employerRequestDto = new EmployerRequestDto();
		employerRequestDto.setEmail("ak896@gmail.com");
		employerRequestDto.setPassword("123456");
		Employer employer = new Employer();
		employer.setEmail("ak896@gmail.com");
		employer.setPassword("12345");
		Employer updatedEmployer = new Employer();
		updatedEmployer.setEmail("ak896@gmail.com");
		updatedEmployer.setPassword("123456");
		when(employerRepository.findByEmailAndStatus("ak896@gmail.com", EmployerStatus.ACTIVE)).thenReturn(employer);
		when(employerRepository.save(any(Employer.class))).thenReturn(updatedEmployer);
		Response response = (Response) employerServiceImpl.updateEmployer(employerRequestDto);
		logger.info("Response is :{}", response);
		assertEquals("123456", ((Employer) response.getData()).getPassword());
	}



}
