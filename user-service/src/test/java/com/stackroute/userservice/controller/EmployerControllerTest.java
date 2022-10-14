package com.stackroute.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.dto.EmployerRequestDto;
import com.stackroute.userservice.dto.EmployerResponseDto;
import com.stackroute.userservice.dto.PaginationResponseDto;
import com.stackroute.userservice.modal.Employer;
import com.stackroute.userservice.serviceimpl.EmployerServiceImpl;
import com.stackroute.userservice.utility.Constants;
import com.stackroute.userservice.utility.Response;

@ExtendWith(MockitoExtension.class)
class EmployerControllerTest {

	@Mock
	private Environment environment;

	@Mock
	private EmployerServiceImpl employerServiceImpl;

	@InjectMocks
	private EmployerController employerController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(employerController).build();
	}

	@AfterEach
	void cleanUp() {
		mockMvc = null;
	}

	@Test
	@DisplayName("Testing add employer controller")
	void testaddEmployer() throws Exception {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		employerRequestDto.setEmail("a1@gmail.com");
		employerRequestDto.setPassword("12345");
		Employer employer = new Employer();
		employer.setEmail("a123@gmail.com");
		employer.setPassword("12345");
		Response<Object> responseFromServiceImpl = new Response<>(employer,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.EMPLOYER_SAVED_SUCCESSFULLY));
		when(employerServiceImpl.addEmployer(employerRequestDto)).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = employerController.addEmployer(employerRequestDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		mockMvc.perform(post("/v1/employerRegistration").contentType(MediaType.APPLICATION_JSON)
				.content(jsontoString(employerRequestDto))).andExpect(status().isOk());
		verify(employerServiceImpl, times(2)).addEmployer(any());

	}

	@Test
	@DisplayName("Testing get employer by email controller")
	void testGetEmployerByEmail() throws Exception {
		Employer employer = new Employer();
		employer.setEmail("a645@gmail.com");
		EmployerResponseDto employerResponseDto = new EmployerResponseDto();
		employerResponseDto.setEmail("a645@gmail.com");
		Response<Object> responseFromServiceImpl = new Response<Object>(employerResponseDto,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.EMPLOYER_FETCHED_SUCCESSFULLY));
		when(employerServiceImpl.getEmployerByEmail("a645@gmail.com")).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = employerController.getEmployerByEmail("a645@gmail.com");
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		this.mockMvc.perform(get("/v1/getEmployer/{email}", "ak645@gmail.com")).andExpect(status().isOk());
		verify(employerServiceImpl, times(2)).getEmployerByEmail(any());

	}

	@Test
	@DisplayName("Testing get all employer controller")
	void testGetAllEmployerController() throws Exception {
		EmployerResponseDto employerResponseDto = new EmployerResponseDto();
		employerResponseDto.setEmail("e500@gmail.com");
		EmployerResponseDto employerResponseDto2 = new EmployerResponseDto();
		employerResponseDto2.setEmail("e600@gmail.com");
		List<EmployerResponseDto> employerResponseDtoList = new ArrayList<>();
		employerResponseDtoList.add(employerResponseDto);
		employerResponseDtoList.add(employerResponseDto2);
		PaginationResponseDto<EmployerResponseDto> paginationResponseDto = new PaginationResponseDto<>();
		paginationResponseDto.setPaginatedData(employerResponseDtoList);
		Response<Object> responseFromServiceImpl = new Response<Object>(paginationResponseDto,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.EMPLOYER_FETCHED_SUCCESSFULLY));
		Pageable pageable = PageRequest.of(0, 40);
		when(employerServiceImpl.getAllEmployers(pageable, "")).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = employerController.getAllEmployers(0, 40, "");
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		this.mockMvc.perform(get("/v1/getAllEmployers").param("page", "0").param("size", "40").param("searchParam", ""))
				.andExpect(status().isOk());
		verify(employerServiceImpl, times(2)).getAllEmployers(pageable, "");

	}

	@Test
	@DisplayName("Test update employer controller")
	void testUpdateEmployerController() throws Exception {
		EmployerRequestDto employerRequestDto = new EmployerRequestDto();
		employerRequestDto.setEmail("a1@gmail.com");
		employerRequestDto.setPassword("12345");
		Employer employer = new Employer();
		employer.setEmail("a1@gmail.com");
		employer.setPassword("12345");
		Response<Object> responseFromServiceImpl = new Response<>(employer,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.EMPLOYER_UPDATED_SUCCESSFULLY));
		lenient().when(employerServiceImpl.updateEmployer(employerRequestDto)).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = employerController.addEmployer(employerRequestDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		mockMvc.perform(put("/v1/profieUpdationEmployer").contentType(MediaType.APPLICATION_JSON)
				.content(jsontoString(employerRequestDto))).andExpect(status().isOk());
		verify(employerServiceImpl, times(1)).updateEmployer(any());
	}

	private String jsontoString(final Object obj) {
		String result;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonContent = objectMapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException ex) {
			result = "error while converting to string";
		}
		return result;
	}

}
