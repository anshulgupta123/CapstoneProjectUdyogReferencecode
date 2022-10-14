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
import com.stackroute.userservice.dto.PaginationResponseDto;
import com.stackroute.userservice.dto.SeekerDto;
import com.stackroute.userservice.dto.SeekerResponseDto;
import com.stackroute.userservice.modal.Seeker;
import com.stackroute.userservice.serviceimpl.SeekerServiceImpl;
import com.stackroute.userservice.utility.Constants;
import com.stackroute.userservice.utility.Response;

@ExtendWith(MockitoExtension.class)
class SeekerControllerTest {

	@Mock
	private Environment environment;

	@Mock
	private SeekerServiceImpl seekerServiceImpl;

	@InjectMocks
	private SeekerController seekerController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(seekerController).build();
	}

	@AfterEach
	void cleanUp() {
		mockMvc = null;
	}

	@Test
	@DisplayName("Testing add seeker controller")
	void testaddSeeker() throws Exception {
		SeekerDto seekerDto = new SeekerDto();
		seekerDto.setEmail("a1@gmail.com");
		seekerDto.setPassword("12345");
		Seeker seeker = new Seeker();
		seeker.setEmail("a1@gmail.com");
		seeker.setPassword("12345");
		Response<Object> responseFromServiceImpl = new Response<>(seeker,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.SEEKER_SAVED_SUCCESSFULLY));
		when(seekerServiceImpl.addSeeker(seekerDto)).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = seekerController.addSeeker(seekerDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		mockMvc.perform(
				post("/v1/seekerRegistration").contentType(MediaType.APPLICATION_JSON).content(jsontoString(seekerDto)))
				.andExpect(status().isOk());
		verify(seekerServiceImpl, times(2)).addSeeker(any());

	}

	@Test
	@DisplayName("Testing get seeker by email controller")
	void testGetSeekerByEmail() throws Exception {
		Seeker seeker = new Seeker();
		seeker.setEmail("a6@gmail.com");
		SeekerResponseDto seekerResponseDto = new SeekerResponseDto();
		seekerResponseDto.setEmail("a6@gmail.com");
		Response<Object> responseFromServiceImpl = new Response<Object>(seekerResponseDto,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.SEEKERS_FETCHED_SUCCESSFULLY));
		when(seekerServiceImpl.getSeekerByEmail("a6@gmail.com")).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = seekerController.getSeekerByEmail("a6@gmail.com");
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		this.mockMvc.perform(get("/v1/getSeeker/{email}", "ak6@gmail.com")).andExpect(status().isOk());
		verify(seekerServiceImpl, times(2)).getSeekerByEmail(any());

	}

	@Test
	@DisplayName("Testing get all seeker controller")
	void testGetAllSeekerController() throws Exception {
		SeekerResponseDto seekerResponseDto = new SeekerResponseDto();
		seekerResponseDto.setEmail("e500@gmail.com");
		SeekerResponseDto seekerResponseDto2 = new SeekerResponseDto();
		seekerResponseDto2.setEmail("e600@gmail.com");
		List<SeekerResponseDto> seekerResponseDtoList = new ArrayList<>();
		seekerResponseDtoList.add(seekerResponseDto);
		seekerResponseDtoList.add(seekerResponseDto2);
		PaginationResponseDto<SeekerResponseDto> paginationResponseDto = new PaginationResponseDto<>();
		paginationResponseDto.setPaginatedData(seekerResponseDtoList);
		Response<Object> responseFromServiceImpl = new Response<Object>(paginationResponseDto,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.SEEKERS_FETCHED_SUCCESSFULLY));
		Pageable pageable = PageRequest.of(0, 40);
		when(seekerServiceImpl.getAllSeeker(pageable, "")).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = seekerController.getAllSeeker(0, 40, "");
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		this.mockMvc.perform(get("/v1/getAllSeekers").param("page", "0").param("size", "40").param("searchParam", ""))
				.andExpect(status().isOk());
		verify(seekerServiceImpl, times(2)).getAllSeeker(pageable, "");

	}

	@Test
	@DisplayName("Test update seeker controller")
	void testUpdateSeekerController() throws Exception {
		SeekerDto seekerDto = new SeekerDto();
		seekerDto.setEmail("a1@gmail.com");
		seekerDto.setPassword("12345");
		Seeker seeker = new Seeker();
		seeker.setEmail("a1@gmail.com");
		seeker.setPassword("12345");
		Response<Object> responseFromServiceImpl = new Response<>(seeker,
				environment.getProperty(Constants.SUCCESS_CODE),
				environment.getProperty(Constants.SEEKER_SAVED_SUCCESSFULLY));
		lenient().when(seekerServiceImpl.updateSeeker(seekerDto)).thenReturn(responseFromServiceImpl);
		ResponseEntity<Object> responseFromController = seekerController.addSeeker(seekerDto);
		assertEquals(HttpStatus.OK, responseFromController.getStatusCode());
		mockMvc.perform(put("/v1/profieUpdationSeeker").contentType(MediaType.APPLICATION_JSON)
				.content(jsontoString(seekerDto))).andExpect(status().isOk());
		verify(seekerServiceImpl, times(1)).updateSeeker(any());
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
