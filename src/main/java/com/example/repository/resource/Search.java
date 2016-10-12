package com.example.repository.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Search extends JsonResource {
	@JsonProperty("_links")
	private Links _links = null;
	
	public Search() {};
	
	public void setLinks(Links _links) {
		this._links = _links;
	}
	
	@Override
	public String toString() {
		return String.format("search [%s]", this._links.toString());
	}
}
