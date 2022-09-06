package com.request.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ErrorControllerTest {
	@InjectMocks
	private ErrorController errorController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(errorController).build();
	}

	@Test
	public void test() {
		assertNotNull(errorController);
	}

	@Test
	public void testError() throws Exception {
		this.mockMvc.perform(get("/error/500")).andExpect(jsonPath("$.msg").value("Internal server error"))
				.andExpect(jsonPath("$.status").value(500)).andExpect(status().isOk());
	}

}
