package com.example.Homebrew;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.Homebrew.rest.Brew;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // try random ports until it finds a free one
@AutoConfigureMockMvc

@Sql(scripts = { "classpath:HomeBrewSchema.sql",
		"classpath:homeBrewData.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("test")

public class HomeBrewContgrollerIntegrationTest {

	@Autowired // tells Spring to inject the MockMVC object into this class
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper; // yanks the class Spring uses to convert java to JSON

	@Test
	void testCreate() throws Exception {
		Brew testKit = new Brew("Cider", "Stawberry", 7, 9);

		// convert to json
		String testKitAsJSON = this.mapper.writeValueAsString(testKit);

		System.out.println(testKit);
		System.out.println(testKitAsJSON);

		// body, method, address and the content-type header
		RequestBuilder request = post("/createBrew").contentType(MediaType.APPLICATION_JSON).content(testKitAsJSON);

		// check the response body and status

		ResultMatcher checkStatus = status().is(201);

		Brew testCreatedKit = new Brew("Cider", "Stawberry", 7, 9);
		testCreatedKit.setId(2); // due to the AUTO_INCREMENT
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedKit);

		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);
		// SEND request and check status + body
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testCreateAbridged() throws Exception {
		this.mockMVC
				.perform(post("/createBrew").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(new Brew("Cider", "Stawberry", 7, 9))))
				.andExpect(status().is(201))
				.andExpect(content().json(this.mapper.writeValueAsString(new Brew(2, "Cider", "Stawberry", 7, 9))));
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/deleteBrew/1"); // create request

		// check response
		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted: 1");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGet() throws Exception {
		RequestBuilder request = get("/getBrews/1");// create request

		// check response
		ResultMatcher checkStatus = status().is(202);

		this.mockMVC.perform(request).andExpect(checkStatus);

	}

	@Test
	void testReplaceBrew() throws Exception {
		int id = 1;
		Brew testKit = new Brew(id, "Cider", "Apple", 8, 10);
		String testKitAsJSON = this.mapper.writeValueAsString(testKit);

		RequestBuilder request = put("/replaceBrew/1").contentType(MediaType.APPLICATION_JSON).content(testKitAsJSON);

		// check response
		ResultMatcher checkStatus = status().is(202);
		ResultMatcher checkBody = content().json(testKitAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void findById() throws Exception {
		RequestBuilder req = get("/getBrews/1");

		ResultMatcher checkStatus = status().is(202);

		Brew testBrew = new Brew(1, "Cider", "On The Rocks", 6, 10);

		String testBrewAsJSON = this.mapper.writeValueAsString(testBrew);

		ResultMatcher checkBody = content().json(testBrewAsJSON);

		this.mockMVC.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testFindByType() throws Exception {
		RequestBuilder request = get("/getByType/Cider");

		ResultMatcher checkStatus = status().isOk();

		List<Brew> testBrew = List.of(new Brew(1, "Cider", "On The Rocks", 6, 10));

		String testBrewAsJSON = this.mapper.writeValueAsString(testBrew);

		ResultMatcher checkBody = content().json(testBrewAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
}
