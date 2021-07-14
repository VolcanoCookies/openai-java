package com.theokanning.openai.finetunes;

import com.theokanning.openai.files.FileResult;
import lombok.Data;

import java.util.List;

@Data
public class FineTuneResult {
	
	public String id;
	
	public String object;
	
	public String model;
	
	public Long createdAt;
	
	public List<FineTuneEvent> events;
	
	public String fineTuneModel;
	
	public Hyperparams hyperparams;
	
	public String organizationId;
	
	public List<FileResult> resultFiles;
	
	public String status;
	
	public List<FileResult> validationFiles;
	
	public List<FileResult> trainingFiles;
	
	public Long updatedAt;
	
	public String userId;
	
}

