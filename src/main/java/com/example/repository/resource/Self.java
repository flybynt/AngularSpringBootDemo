package com.example.repository.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Self extends JsonResource {
	private String href = null;
	
	public Self() {}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return String.format("self [href=%s]", this.getHref());
	}
}
