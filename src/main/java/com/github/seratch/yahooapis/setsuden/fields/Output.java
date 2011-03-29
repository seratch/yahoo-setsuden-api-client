package com.github.seratch.yahooapis.setsuden.fields;

public enum Output {

	xml("xml"), php("php"), json("json");

	private String value;

	private Output(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
