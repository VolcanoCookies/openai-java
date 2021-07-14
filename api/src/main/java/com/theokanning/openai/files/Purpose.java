package com.theokanning.openai.files;

/**
 * The different purposes a file can have.
 * <br>
 * <ul>
 *     <li>{@link #SEARCH}</li>
 *     <li>{@link #ANSWERS}</li>
 *     <li>{@link #CLASSIFICATION}</li>
 * </ul>
 */
public enum Purpose {
	/**
	 * Files used for searching.
	 */
	SEARCH("search"),
	/**
	 * Files used for answers.
	 */
	ANSWERS("answers"),
	/**
	 * Files used for classification.
	 */
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
