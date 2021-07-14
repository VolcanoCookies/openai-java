package com.theokanning.openai.files;

import lombok.Data;

@Data
public class FileResult {
	
	/**
	 * Unique id of this file.
	 */
	public String id;
	
	/**
	 * What type of object this is, should be <code>file</code>.
	 */
	public String object;
	
	/**
	 * The size of the file in bytes.
	 */
	public Long bytes;
	
	/**
	 * When the file was created.
	 */
	public Long createdAt;
	
	/**
	 * The name of this file.
	 */
	public String filename;
	
	/**
	 * The purpose of this file.
	 *
	 * @see Purpose
	 */
	public Purpose purpose;
	
}
