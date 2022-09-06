package com.request.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.request.model.Request;
import com.request.service.IRequestService;

public class RequestControllerTest {
	@InjectMocks
	private RequestController requestController;

	private MockMvc mockMvc;

	@Mock
	private IRequestService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
	}

	@Test
	public void test() {
		assertNotNull(requestController);
	}

	@Test
	public void testGetRequestDataByCustomerId() throws Exception {
		mockMvc.perform(get("/requests/").param("action", "read").param("customerId", "1")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllCustomerRequests() throws Exception {
		List<Request> list = Arrays.asList(new Request(1, "Citibank", "Mumbai", "600037002", "CITI0000003", "34645645",
				"100231", "Mr", "John Snow", "M", "10/09/1998", "Mumbai", "9655567890", "john@gmail.com", null,
				"Jack Snow", null, "father", "Yes", "Jill", "1000", "100", null, "6", "AGPAY4562R"));
		when(service.getAllCustomerRequests()).thenReturn(list);
		mockMvc.perform(get("/requests/all").param("action", "read")).andExpect(status().isOk());
	}

	@Test
	public void testSaveRequest() throws Exception {
		Request req = new Request(0, "Citibank", "Mumbai", "600037002", "CITI0000003", "34645645", "5465462", "Mr",
				"John Snow", "M", "10/09/1998", "Mumbai", "9655567890", "john@gmail.com", null, "Jack Snow", null,
				"father", "Yes", "Jill", "1000", "100", null, "6", "AGPAY4562R");
		mockMvc.perform(post("/requests/").param("action", "create").content(jsonString(req))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.msg").value("Request data saved successfully")).andExpect(status().isOk());
	}

	public String jsonString(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
