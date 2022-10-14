package com.stackroute.userservice.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.userservice.dto.PaginationResponseDto;
import com.stackroute.userservice.dto.SeekerDto;
import com.stackroute.userservice.dto.SeekerResponseDto;
import com.stackroute.userservice.enums.SeekerStatus;
import com.stackroute.userservice.exception.SeekerException;
import com.stackroute.userservice.modal.Seeker;
import com.stackroute.userservice.repository.EmployerRepository;
import com.stackroute.userservice.repository.SeekerRepository;
import com.stackroute.userservice.utility.Response;

@ExtendWith(MockitoExtension.class)
class SeekerServiceImplTest {

	Logger logger = LoggerFactory.getLogger(SeekerServiceImplTest.class);

	@Mock
	private Environment environment;

	@Mock
	private SeekerRepository seekerRepository;
	
	@Mock
	private EmployerRepository employerRepository;

	@InjectMocks
	private SeekerServiceImpl seekerServiceImpl;
	


	@Test
	@DisplayName("Testing get seeker by email")
	void testgetSeekerByEmail() {
		Seeker seeker = new Seeker();
		seeker.setEmail("e1@gmail.com");
		seeker.setPassword("abcd");
		when(seekerRepository.findByEmailAndStatus("e1@gmail.com", SeekerStatus.ACTIVE)).thenReturn(seeker);
		Response object = (Response) seekerServiceImpl.getSeekerByEmail("e1@gmail.com");
		logger.info("RESponse is :{}", object.toString());
		SeekerResponseDto seekerResponseDto = (SeekerResponseDto) object.getData();
		assertEquals("e1@gmail.com", seekerResponseDto.getEmail());

	}

	@Test
	@DisplayName("Testing get seeker by email for empty input")
	void testGetSeekerByEmailEmptyInput() {
		assertThrows(SeekerException.class, () -> seekerServiceImpl.getSeekerByEmail(""));
	}

	@Test
	@DisplayName("Testing get seeker by email when seeker is null")
	void testGetSeekerByEmailSeekerIsNull() {
		when(seekerRepository.findByEmailAndStatus("e34@gmail.com", SeekerStatus.ACTIVE)).thenReturn(null);
		assertThrows(SeekerException.class, () -> seekerServiceImpl.getSeekerByEmail("e34@gmail.com"));
	}

	@Test
	@DisplayName("Testing get All Sekker when there is no data from repo")
	void testGetAllSeeekerNoSeekerFound() {
		Pageable pageable = PageRequest.of(0, 40);
		when(seekerRepository.findByNameLikeOrLocationLikeOrNoticePeriodLikeOrDomainLikeOrderByName("", "", "", "",
				pageable)).thenReturn(null);
		assertThrows(SeekerException.class, () -> seekerServiceImpl.getAllSeeker(pageable, ""));

	}

	@Test
	@DisplayName("Testing get All Sekker Data and Pagination Object Both EXist Or Not")
	void testGetAllSeeeker() {
		Seeker seeker = new Seeker();
		seeker.setEmail("e500@gmail.com");
		Seeker seeker2 = new Seeker();
		seeker2.setEmail("e600@gmail.com");
		List<Seeker> seekerList = new ArrayList<>();
		seekerList.add(seeker);
		seekerList.add(seeker2);
		Page<Seeker> dataFromRepo = new PageImpl<>(seekerList);
		Pageable pageable = PageRequest.of(0, 40);
		when(seekerRepository.findByNameLikeOrLocationLikeOrNoticePeriodLikeOrDomainLikeOrderByName("", "", "", "",
				pageable)).thenReturn(dataFromRepo);
		Response response = (Response) seekerServiceImpl.getAllSeeker(pageable, "");
		PaginationResponseDto<SeekerResponseDto> paginationResponseDto = (PaginationResponseDto<SeekerResponseDto>) response
				.getData();
		assertEquals(2, paginationResponseDto.getPaginatedData().size());
		assertNotNull(paginationResponseDto.getPageable());

	}

	@Test
	@DisplayName("Testing add seeker for empty mail")
	void testAddSeekerForEmptyEmail() {
		SeekerDto seekerDto = new SeekerDto();
		seekerDto.setPassword("12345");
		assertThrows(SeekerException.class, () -> seekerServiceImpl.addSeeker(seekerDto));
	}

	@Test
	@DisplayName("Tesing add seeker for empty password")
	void testaddSeekerForEmptyPassword() {
		SeekerDto seekerDto = new SeekerDto();
		seekerDto.setEmail("ad@gmail.com");
		assertThrows(SeekerException.class, () -> seekerServiceImpl.addSeeker(seekerDto));

	}

	@Test
	@DisplayName("Tesing add seeker for empty password and mail")
	void testaddSeekerForEmptyMailAndPassword() {
		SeekerDto seekerDto = new SeekerDto();
		assertThrows(SeekerException.class, () -> seekerServiceImpl.addSeeker(seekerDto));
	}

	@Test
	@DisplayName("Tesing add seeker seeker already exist")
	void testaddSeekerSeakerExist() {
		SeekerDto seekerDto = new SeekerDto();
		Seeker seeker = new Seeker();
		seeker.setEmail("akg@gmail.com");
		when(seekerRepository.findByEmailAndStatus("akg@gmail.com", SeekerStatus.ACTIVE)).thenReturn(seeker);
		seekerDto.setEmail("akg@gmail.com");
		seekerDto.setPassword("12345");
		assertThrows(SeekerException.class, () -> seekerServiceImpl.addSeeker(seekerDto));
	}

	@Test
	@DisplayName("Tesing add seeker")
	void testaddSeeker() {
		SeekerDto seekerDto = new SeekerDto();
		Seeker seeker = new Seeker();
		seeker.setEmail("ak89@gmail.com");
		seeker.setPassword("12345");
		when(seekerRepository.findByEmailAndStatus("ak89@gmail.com", SeekerStatus.ACTIVE)).thenReturn(null);
		when(seekerRepository.save(any(Seeker.class))).thenReturn(seeker);
		seekerDto.setEmail("ak89@gmail.com");
		seekerDto.setPassword("12345");
		Response response = (Response) seekerServiceImpl.addSeeker(seekerDto);
		logger.info("Response is :{}", response);
		assertEquals("ak89@gmail.com", ((Seeker) response.getData()).getEmail());
	}

	@Test
	@DisplayName("Testing update seeker for empty email")
	void testUpdateSeekerEmptyEmail() {
		SeekerDto seekerDto = new SeekerDto();
		assertThrows(SeekerException.class, () -> seekerServiceImpl.updateSeeker(seekerDto));
	}

	@Test
	@DisplayName("Testing update seeker when does not exist")
	void testUpdateSeekerSeekerDoesNotExist() {
		SeekerDto seekerDto = new SeekerDto();
		seekerDto.setEmail("ak89@gmail.com");
		when(seekerRepository.findByEmailAndStatus("ak89@gmail.com", SeekerStatus.ACTIVE)).thenReturn(null);
		assertThrows(SeekerException.class, () -> seekerServiceImpl.updateSeeker(seekerDto));
	}

	@Test
	@DisplayName("Testing update seeker")
	void testUpdateSeeker() {
		SeekerDto seekerDto = new SeekerDto();
		seekerDto.setEmail("ak89@gmail.com");
		seekerDto.setPassword("123456");
		Seeker seeker = new Seeker();
		seeker.setEmail("ak89@gmail.com");
		seeker.setPassword("12345");
		Seeker updatedSeeker = new Seeker();
		updatedSeeker.setEmail("ak89@gmail.com");
		updatedSeeker.setPassword("123456");
		when(seekerRepository.findByEmailAndStatus("ak89@gmail.com", SeekerStatus.ACTIVE)).thenReturn(seeker);
		when(seekerRepository.save(any(Seeker.class))).thenReturn(updatedSeeker);
		Response response = (Response) seekerServiceImpl.updateSeeker(seekerDto);
		logger.info("Response is :{}", response);
		assertEquals("123456", ((Seeker) response.getData()).getPassword());
	}

}
