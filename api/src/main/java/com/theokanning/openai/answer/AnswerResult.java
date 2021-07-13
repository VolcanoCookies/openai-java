package com.theokanning.openai.answer;

import lombok.Data;

import java.util.List;

@Data
public class AnswerResult {
	
	List<String> answers;
	
	String completion;
	
	String text;
	
	String model;
	
	String object;
	
	String searchModel;
	
	List<Document> selectedDocuments;
	
}
