package com.example.repository.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.example.repository.resource.Search;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoSearchTest  {
	String repoSearchURL = null;
	String searchJSON = null;
	Search search = null;
	MockRestServiceServer server = null;
	@Autowired RestTemplate restTemplate = null;
	
	public RepoSearchTest() {}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// connect to H2 DB
		// remove all data from DB
		// add some data to DB
	}

	@Before
	public void setUp() throws Exception {
		//restTemplate = new RestTemplate();
		repoSearchURL = "/repodemo/search";
		server = MockRestServiceServer.bindTo(restTemplate).build();
		searchJSON = "{\"_links\":{"
			+ "\"findByFirstName\":{\"href\":\"http://localhost:8080/repodemo/search/findByFirstName{?fn}\"},"
			+ "\"findByLastName\":{\"href\":\"http://localhost:8080/repodemo/search/findByLastName{?ln}\"},"
			+ "\"findByEmail\":{\"href\":\"http://localhost:8080/repodemo/search/findByEmail{?em}\"},"
			+ "\"self\":{\"href\":\"http://localhost:8080/repodemo/search\"}}}";
	}

	@Test
	public void itShouldRetun200StatusForHttpGetForRepositorySearchUrl() {
		server.expect(MockRestRequestMatchers.requestTo(repoSearchURL))
			.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
			.andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK));
		
		restTemplate.getForObject(repoSearchURL, Search.class);
		server.verify();
	}

	@Test
	public void itShouldHaveExpectedContentWithResultForRepositorySearchUrl() {
		server.expect(MockRestRequestMatchers.requestTo(repoSearchURL))
			.andRespond(MockRestResponseCreators.withSuccess(searchJSON, MediaType.parseMediaType("application/hal+json")));
		
		String response = restTemplate.getForObject(repoSearchURL, String.class);
		server.verify();
		assertEquals(response, searchJSON);
	}
}
