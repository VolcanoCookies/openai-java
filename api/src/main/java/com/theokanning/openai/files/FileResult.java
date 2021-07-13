package com.theokanning.openai.files;

import lombok.Data;

@Data
public class FileResult {
	
	public String id;
	
	public String object;
	
	public Long bytes;
	
	public Long createdAt;
	
	public String filename;
	
	public Purpose purpose;
	
}
