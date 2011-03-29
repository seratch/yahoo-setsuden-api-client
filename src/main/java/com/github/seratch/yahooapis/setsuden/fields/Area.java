package com.github.seratch.yahooapis.setsuden.fields;

public enum Area {

	tokyo("tokyo");

	private String value;

	private Area(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
