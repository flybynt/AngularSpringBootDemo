package com.example.repository.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindByFirstName extends JsonResource {
	private String href = null;
	
	public FindByFirstName() {}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public String toString() {
		return String.format("findByFirstName [href=%s]", this.getHref());
	}
}
