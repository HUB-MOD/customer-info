package com.hub.customerinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hub.customerinfo.controller.CustomerController;
import com.hub.customerinfo.model.dto.CustomerResponseDto;

import com.hub.customerinfo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	ObjectMapper objectMapper;

	@BeforeEach
	void configure() {

		objectMapper = JsonMapper.builder()
								.findAndAddModules()
								.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
								.build();

	}
	@Test
	void getCustomerByIdTest() throws Exception {

		when(customerService.findCustomerById(12345678L)).thenReturn(new CustomerResponseDto(12345678L,
																									"Juan",
																									"Perez",
																								"DNI",
																											LocalDateTime.parse("2023-02-16T04:08:26.1284925")));

		mockMvc.perform(get("/api/customer/v1/12345678").contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Basic aHViOmFkbWlu"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName").value("Juan"))
				.andExpect(jsonPath("$.lastName").value("Perez"))
				.andExpect(jsonPath("$.documentType").value("DNI"))
				.andExpect(jsonPath("$.responseDate").value("2023-02-16T04:08:26.1284925"));

		verify(customerService).findCustomerById(12345678L);
	}

	@Test
	void getAllCustomersTest() throws Exception {

		CustomerResponseDto customer1 = CustomerResponseDto.builder()
															.documentNumber(12345678L)
															.firstName("Juan")
															.lastName("Perez")
															.documentType("DNI")
															.responseDate(LocalDateTime.parse("2023-02-16T04:08:26.1284925"))
															.build();

		CustomerResponseDto customer2 = CustomerResponseDto.builder()
															.documentNumber(12341234L)
															.firstName("Alex")
															.lastName("Luna")
															.documentType("PASSPORT")
															.responseDate(LocalDateTime.parse("2023-02-16T04:08:26.1284925"))
															.build();

		List<CustomerResponseDto> responseDtoList = new ArrayList<>();
		responseDtoList.add(customer1);
		responseDtoList.add(customer2);

		System.out.println(objectMapper.writeValueAsString(responseDtoList));

		when(customerService.findAll()).thenReturn(responseDtoList);


		mockMvc.perform(get("/api/customer/v1/all").contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Basic aHViOmFkbWlu"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))

				.andExpect(jsonPath("$[0].documentNumber").value("12345678"))
				.andExpect(jsonPath("$[0].firstName").value("Juan"))
				.andExpect(jsonPath("$[0].lastName").value("Perez"))
				.andExpect(jsonPath("$[0].documentType").value("DNI"))
				.andExpect(jsonPath("$[0].responseDate").value("2023-02-16T04:08:26.1284925"))

				.andExpect(jsonPath("$[1].documentNumber").value("12341234"))
				.andExpect(jsonPath("$[1].firstName").value("Alex"))
				.andExpect(jsonPath("$[1].lastName").value("Luna"))
				.andExpect(jsonPath("$[1].documentType").value("PASSPORT"))
				.andExpect(jsonPath("$[1].responseDate").value("2023-02-16T04:08:26.1284925"))

				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(content().json(objectMapper.writeValueAsString(responseDtoList)));

		verify(customerService).findAll();

	}

}
