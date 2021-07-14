package com.theokanning.openai.answer;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Answers the specified question using the provided documents and examples.</p>
 * <p>The endpoint first <a href="https://beta.openai.com/docs/api-reference/searches">searches</a> over provided documents or files to find relevant context. The relevant context is combined with the provided examples and question to create the prompt for <a href="https://beta.openai.com/docs/api-reference/completions">completion</a>.</p>
 *
 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create">Online Docs</a>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequest {
	
	/**
	 * <p>ID of the engine to use for completion.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-model">Online Docs</a>
	 */
	@NonNull
	String model;
	
	/**
	 * <p>Question to get answered.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-question">Online Docs</a>
	 */
	@NonNull
	String question;
	
	/**
	 * <p>List of (question, answer) pairs that will help steer the model towards the tone and answer format you'd like. We recommend adding 2 to 3 examples.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-examples">Online Docs</a>
	 */
	@NonNull
	@Singular("example")
	List<String> examples;
	
	/**
	 * <p>A text snippet containing the contextual information used to generate the answers for the <code>{@link #examples}</code> you provide.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-examples_context">Online Docs</a>
	 */
	@NonNull
	String examplesContext;
	
	/**
	 * <p>List of documents from which the answer for the input <code>{@link #question}</code> should be derived. If this is an empty list, the question will be answered based on the question-answer examples.</p>
	 * <p>You should specify either <code>{@link #documents}</code> or a <code>{@link #file}</code>, but not both.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-documents">Online Docs</a>
	 */
	@Singular("document")
	List<String> documents;
	
	/**
	 * <p>The ID of an uploaded file that contains documents to search over. See <a href="https://beta.openai.com/docs/api-reference/files/upload">upload file</a> for how to upload a file of the desired format and purpose.</p>
	 * <p>You should specify either <code>{@link #documents}</code> or a <code>{@link #file}</code>, but not both.</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-file">Online Docs</a>
	 */
	String file;
	
	/**
	 * <p>ID of the engine to use for <a href="https://beta.openai.com/docs/api-reference/searches/create">Search</a>.</p>
	 * <p>
	 * <code>Defaults to ada</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-search_model">Online Docs</a>
	 */
	String searchModel;
	
	/**
	 * <p>The maximum number of documents to be ranked by <a href="https://beta.openai.com/docs/api-reference/searches/create">Search</a> when using <code>{@link #file}</code>. Setting it to a higher value leads to improved accuracy but with increased latency and cost.</p>
	 * <p>
	 * <code>Defaults to 200</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-max_rerank">Online Docs</a>
	 */
	Integer maxRerank;
	
	/**
	 * <p>What <a href="https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277" target="_blank" rel="noopener noreferrer">sampling temperature</a> to use. Higher values mean the model will take more risks and value 0 (argmax sampling) works better for scenarios with a well-defined answer.</p>
	 * <p>
	 * <code>Defaults to 0</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-temperature">Online Docs</a>
	 */
	Double temperature;
	
	/**
	 * <p>Include the log probabilities on the <code>{@link #logprobs}</code> most likely tokens, as well the chosen tokens. For example, if <code>{@link #logprobs}</code> is 10, the API will return a list of the 10 most likely tokens. the API will always return the <code>logprob</code> of the sampled token, so there may be up to <code>logprobs+1</code> elements in the response.</p>
	 * <p>When <code>{@link #logprobs}</code> is set, <code>completion</code> will be automatically added into <code>{@link #expand}</code> to get the logprobs.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-logprobs">Online Docs</a>
	 */
	Integer logprobs;
	
	/**
	 * <p>The maximum number of tokens allowed for the generated answer</p>
	 * <p>
	 * <code>Defaults to 16</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-max_tokens">Online Docs</a>
	 */
	Integer maxTokens;
	
	/**
	 * <p>Up to 4 sequences where the API will stop generating further tokens. The returned text will not contain the stop sequence.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-stop">Online Docs</a>
	 */
	@Singular("stop")
	List<String> stop;
	
	/**
	 * <p>How many answers to generate for each question.</p>
	 * <p>
	 * <code>Defaults to 1</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-n">Online Docs</a>
	 */
	Integer n;
	
	/**
	 * <p>Modify the likelihood of specified tokens appearing in the completion.</p>
	 * <p>Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this <a href="https://beta.openai.com/tokenizer?view=bpe">tokenizer tool</a> (which works for both GPT-2 and GPT-3) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.</p>
	 * <p>As an example, you can pass <code>{"50256": -100}</code> to prevent the &lt;|endoftext|&gt; token from being generated.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-logit_bias">Online Docs</a>
	 */
	@Singular("logitBias")
	Map<String, Integer> logitBias;
	
	/**
	 * <p>A special boolean flag for showing metadata. If set to <code>true</code>, each document entry in the returned JSON will contain a "metadata" field.</p>
	 * <p>This flag only takes effect when <code>{@link #file}</code> is set.</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-return_metadata">Online Docs</a>
	 */
	Boolean returnMetadata;
	
	/**
	 * <p>If set to <code>true</code>, the returned JSON will include a "prompt" field containing the final prompt that was used to request a completion. This is mainly useful for debugging purposes.</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-return_prompt">Online Docs</a>
	 */
	Boolean returnPrompt;
	
	/**
	 * <p>If an object name is in the list, we provide the full information of the object; otherwise, we only provide the object ID. Currently we support <code>completion</code> and <code>{@link #file}</code> objects for expansion.</p>
	 * <p>
	 * <code>Defaults to []</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/answers/create#answers/create-expand">Online Docs</a>
	 */
	@Singular("expand")
	List<String> expand;
	
}