package com.example.repository.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links extends JsonResource {
	private FindByFirstName findByFirstName = null;
	private FindByLastName findByLastName = null;
	private FindByEmail findByEmail = null;
	private Self self = null;
	
	public Links() {}

	public FindByFirstName getFindByFirstName() {
		return findByFirstName;
	}

	public void setFindByFirstName(FindByFirstName findByFirstName) {
		this.findByFirstName = findByFirstName;
	}

	public FindByLastName getFindByLastName() {
		return findByLastName;
	}

	public void setFindByLastName(FindByLastName findByLastName) {
		this.findByLastName = findByLastName;
	}

	public FindByEmail getFindByEmail() {
		return findByEmail;
	}

	public void setFindByEmail(FindByEmail findByEmail) {
		this.findByEmail = findByEmail;
	}

	public Self getSelf() {
		return self;
	}
	
	public void setSelf(Self self) {
		this.self = self;
	}
	
	@Override
	public String toString() {
		return String.format("_links [findbyFirstName=%s, findbyLastName=%s, findbyEmail=%s, self=%s]",
				this.getFindByFirstName(),
				this.getFindByLastName(),
				this.getFindByEmail(),
				this.getSelf());
	}
}
