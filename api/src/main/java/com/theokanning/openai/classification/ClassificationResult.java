package com.theokanning.openai.classification;

import com.theokanning.openai.answer.Document;
import lombok.Data;

import java.util.List;

@Data
public class ClassificationResult {
	
	public String completion;
	
	public String label;
	
	public String model;
	
	public String object;
	
	public String searchModel;
	
	public List<Document> selectedExamples;
	
	public String prompt;
	
}

