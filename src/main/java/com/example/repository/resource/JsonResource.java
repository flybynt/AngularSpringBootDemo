package com.example.repository.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class JsonResource {
	public String toJSON () {
		StringBuilder json = new StringBuilder("");
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			json.append(mapper.writeValueAsString(this));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
