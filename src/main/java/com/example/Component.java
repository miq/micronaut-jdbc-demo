package com.example;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public class Component {

	@Id
	private String ID;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
}
