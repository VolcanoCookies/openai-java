package com.theokanning.openai.search;

import lombok.*;

import java.util.List;

/**
 * <p>The search endpoint computes similarity scores between provided query and documents. Documents can be passed directly to the API if there are no more than 200 of them.</p>
 * <p>To go beyond the 200 document limit, documents can be processed offline and then used for efficient retrieval at query time. When <code>{@link #file}</code> is set, the search endpoint searches over all the documents in the given file and returns up to the <code>{@link #maxRerank}</code> number of documents. These documents will be returned along with their search scores.</p>
 * <p>The similarity score is a positive score that usually ranges from 0 to 300 (but can sometimes go higher), where a score above 200 usually means the document is semantically similar to the query.</p>
 *
 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create">Online Docs</a>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchRequest {
	
	/**
	 * <p>The ID of the engine to use for this request</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create#searches/create-engine_id">Online Docs</a>
	 */
	@NonNull
	String engineId;
	
	/**
	 * <p>Up to 200 documents to search over, provided as a list of strings.</p>
	 * <p>The maximum document length (in tokens) is 2034 minus the number of tokens in the query.</p>
	 * <p>You should specify either <code>{@link #documents}</code> or a <code>{@link #file}</code>, but not both.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create#searches/create-documents">Online Docs</a>
	 */
	List<String> documents;
	
	/**
	 * <p>The ID of an uploaded file that contains documents to search over.</p>
	 * <p>You should specify either <code>{@link #documents}</code> or a <code>{@link #file}</code>, but not both.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create#searches/create-file">Online Docs</a>
	 */
	String file;
	
	/**
	 * <p>Query to search against the documents.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create#searches/create-query">Online Docs</a>
	 */
	@NonNull
	String query;
	
	/**
	 * <p>The maximum number of documents to be re-ranked and returned by search.</p>
	 * <p>This flag only takes effect when <code>{@link #file}</code> is set.</p>
	 * <p>
	 * <code>Defaults to 200</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create#searches/create-max_rerank">Online Docs</a>
	 */
	Integer maxRerank;
	
	/**
	 * <p>A special boolean flag for showing metadata. If set to <code>true</code>, each document entry in the returned JSON will contain a "metadata" field.</p>
	 * <p>This flag only takes effect when <code>{@link #file}</code> is set.</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/searches/create#searches/create-return_metadata">Online Docs</a>
	 */
	Boolean returnMetadata;
	
}