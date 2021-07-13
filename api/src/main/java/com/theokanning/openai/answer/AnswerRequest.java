package com.theokanning.openai.answer;

import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequest {
	
	@NonNull
	String model;
	
	@NonNull
	String question;
	
	@NonNull
	List<List<String>> examples;
	
	@NonNull
	String examplesContext;
	
	List<String> documents;
	
	String file;
	
	String searchModel;
	
	Integer maxRerank;
	
	Integer temperature;
	
	Integer logprobs;
	
	Integer maxTokens;
	
	List<String> stop;
	
	Integer n;
	
	Map<String, Integer> logitBias;
	
	Boolean returnMetadata;
	
	Boolean returnPrompt;
	
	List<String> expand;
	
}

