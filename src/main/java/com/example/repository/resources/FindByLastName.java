package com.example.repository.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindByLastName extends JsonResource {
	private String href = null;
	
	public FindByLastName() {}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public String toString() {
		return String.format("findByLastName [href=%s]", this.getHref());
	}
}
