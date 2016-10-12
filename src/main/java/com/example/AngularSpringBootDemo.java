package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.repository.DataRepository;
import com.example.repository.entity.DataEntity;
import com.example.repository.resource.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AngularSpringBootDemo extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AngularSpringBootDemo.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	// Add some test data so we can test the API separately from the webapp
	// Use some Java 8 lambda and named method functionality :-)
	@Bean
	CommandLineRunner cmdRunner(DataRepository dr, RestTemplate restTemplate) {
		return args -> {
			dr.save(new DataEntity("Jane Doe","JaneDoe@gmail.com"));
			dr.save(new DataEntity("Joe","Blow","joe.blow@hotmail.com", "comment testing 123"));
			dr.save(new DataEntity("John Smoth","johnsmith@yahoo.com", ""));
			dr.save(new DataEntity("Wiley Coyote","wileycoyote@acme.com","Bad |comment $%with some (bad]} chars that will be stripped before persisting to the DB."));
			dr.findAll().forEach(System.out::println);
			//ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/repodemo/search", String.class);
			//ObjectMapper mapper = new ObjectMapper();
			//Search search = mapper.readValue(response.getBody(), Search.class);
			//System.out.println("\n\nHTTP Request Body\n" + response.getBody() + "\n\nJackson Mapping from POJO\n" + search.toJSON());
			//Search search = restTemplate.getForObject("http://localhost:8080/repodemo/search", Search.class);
			//System.out.println("\n\n" + search.toJSON() + "\n\n");
		};		
	}
}