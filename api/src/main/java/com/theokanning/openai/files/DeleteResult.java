package com.theokanning.openai.files;

import lombok.Data;

@Data
public class DeleteResult {
	
	public String id;
	
	public String object;
	
	public Boolean deleted;
	
}
