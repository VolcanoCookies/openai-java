package com.theokanning.openai.search;

import lombok.Data;

/**
 * A search result for a single document.
 * <p>
 *
 * @see <a href="https://beta.openai.com/docs/api-reference/search">https://beta.openai.com/docs/api-reference/search</a>
 */
@Data
public class SearchResult {
	
	/**
	 * The position of this document in the request list
	 */
	public Integer document;
	
	/**
	 * The type of object returned, should be "search_result"
	 */
	public String object;
	
	/**
	 * A number measuring the document's correlation with the query.
	 * A higher score means a stronger relationship.
	 */
	public Double score;
	
}
