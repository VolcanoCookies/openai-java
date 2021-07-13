package com.theokanning.openai.files;

public enum Purpose {
	SEARCH("search"),
	ANSWERS("answers"),
	CLASSIFICATION("classification");
	
	public String name;
	
	Purpose(String s) {
		name = s;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
