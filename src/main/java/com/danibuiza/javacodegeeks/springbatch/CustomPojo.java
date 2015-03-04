package com.danibuiza.javacodegeeks.springbatch;


public class CustomPojo {

	private String id;

	private String description;

	public CustomPojo() {

	}

	public CustomPojo(String id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public String toString() {
		return id + "," + description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
