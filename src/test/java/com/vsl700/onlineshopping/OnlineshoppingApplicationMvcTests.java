package com.vsl700.onlineshopping;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@WebAppConfiguration
public abstract class OnlineshoppingApplicationMvcTests {

	protected MockMvc mvc;

	@Autowired
	private WebApplicationContext webAppContext;

	@BeforeEach
	protected void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> targetClass) throws JsonMappingException, JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, targetClass);
	}

}
