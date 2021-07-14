package com.theokanning.openai.files;

import lombok.Data;

@Data
public class DeleteResult {
	
	/**
	 * Unique id of the file deleted
	 */
	public String id;
	
	public String object;
	
	public Boolean deleted;
	
}
