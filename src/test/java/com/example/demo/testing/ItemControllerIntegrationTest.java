package com.example.demo.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.itemrepo.ItemRepo;
import com.example.rest.Items;
import com.example.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator.Validity;


@ActiveProfiles("test")
@Sql(scripts = {"classpath:item-schema.sql", "classpath:item-data.sql"},
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		
				Items item = new Items("buy beach towel", "medium");
				String itemAsJSON = this.mapper.writeValueAsString(item);
				RequestBuilder mockRequest = 
									post("/items/createItem")
									.contentType(MediaType.APPLICATION_JSON)
									.content(itemAsJSON);
				Items saveditem = new Items("buy beach towel","medium");
				saveditem.setId(2L);
				
				String saveditemAsJSON = this.mapper.writeValueAsString(saveditem);
				ResultMatcher matchStatus = status().isCreated(); 
				ResultMatcher matchBody = content().json(saveditemAsJSON);
				this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
			}
			
	
	@Test
	void testUpdate() throws Exception {
		Long id = 1L;
		Items newitem = new Items(id, "pack bag", "medium");
		String newitemAsJSON = this.mapper.writeValueAsString(newitem);

		RequestBuilder req = put("/items/update/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(newitemAsJSON);

		ResultMatcher checkStatus = status().isAccepted();

		ResultMatcher checkBody = content().json(newitemAsJSON);

		this.mock.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	
	
	@Test
	void testDelete() throws Exception {
			
			Long id = 1L;
			RequestBuilder mockRequest = delete("/items/deleteItem/" + id);
			
			ResultMatcher matchStatus = status().isNoContent(); 
			
			this.mock.perform(mockRequest).andExpect(matchStatus);
	
	}
	

	@Test
	void testRead() throws Exception {
		
		RequestBuilder mockRequest = get("/items/readAll");
		
		ResultMatcher matchStatus = status().is(200); 
		
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
	
	
	
	
	

	
	}
		
