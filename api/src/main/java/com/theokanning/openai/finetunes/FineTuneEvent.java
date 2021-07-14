package com.theokanning.openai.finetunes;

import lombok.Data;

@Data
public class FineTuneEvent {
	
	/**
	 * What type of object this is, should be <code>fine-tune-event</code>
	 */
	public String object;
	
	/**
	 * When the event was created.
	 */
	public Long createdAt;
	
	/**
	 * Severity level of the event.
	 */
	public String level;
	
	/**
	 * Short description of the event, such as 'Job started.'.
	 */
	public String message;
	
}

