package com.theokanning.openai.classification;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Classifies the specified <code>{@link #query}</code> using provided examples.</p>
 * <p>The endpoint first <a href="https://beta.openai.com/docs/api-reference/searches">searches</a> over the labeled examples to select the ones most relevant for the particular query. Then, the relevant examples are combined with the query to construct a prompt to produce the final label via the <a href="https://beta.openai.com/docs/api-reference/completions">completions</a> endpoint.</p>
 * <p>Labeled examples can be provided via an uploaded <code>{@link #file}</code>, or explicitly listed in the request using the <code>{@link #examples}</code> parameter for quick tests and small scale use cases.</p>
 * <a href="https://beta.openai.com/docs/api-reference/classifications/create">Online Docs</a>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassificationRequest {
	
	/**
	 * <p>ID of the engine to use for completion.</p>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-model">Online Docs</a>
	 */
	@NonNull
	String model;
	
	/**
	 * <p>Query to be classified.</p>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-query">Online Docs</a>
	 */
	@NonNull
	String query;
	
	/**
	 * <p>A list of examples with labels, in the following format:</p>
	 * <p><code>[["The movie is so interesting.", "Positive"], ["It is quite boring.", "Negative"], ...]</code></p>
	 * <p>All the label strings will be normalized to be capitalized.</p>
	 * <p>You should specify either <code>{@link #examples}</code> or <code>{@link #file}</code>, but not both. </p>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-examples">Online Docs</a>
	 */
	List<String> examples;
	
	/**
	 * <p>The ID of the uploaded file that contains training examples. See <a href="https://beta.openai.com/docs/api-reference/files/upload">upload file</a> for how to upload a file of the desired format and purpose.</p>
	 * <p>You should specify either <code>{@link #examples}</code> or <code>{@link #file}</code>, but not both. </p>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-file">Online Docs</a>
	 */
	String file;
	
	/**
	 * <p>The set of categories being classified. If not specified, candidate labels will be automatically collected from the examples you provide. All the label strings will be normalized to be capitalized.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-labels">Online Docs</a>
	 */
	List<String> labels;
	
	/**
	 * <p>ID of the engine to use for <a href="https://beta.openai.com/docs/api-reference/searches/create">Search</a>.</p>
	 * <p>
	 * <code>Defaults to ada</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-search_model">Online Docs</a>
	 */
	String searchModel;
	
	/**
	 * <p>What sampling <code>{@link #temperature}</code> to use. Higher values mean the model will take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.</p>
	 * <p>
	 * <code>Defaults to 0</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-temperature">Online Docs</a>
	 */
	Number temperature;
	
	/**
	 * <p>Include the log probabilities on the <code>{@link #logprobs}</code> most likely tokens, as well the chosen tokens. For example, if <code>{@link #logprobs}</code> is 10, the API will return a list of the 10 most likely tokens. the API will always return the <code>logprob</code> of the sampled token, so there may be up to <code>logprobs+1</code> elements in the response.</p>
	 * <p>When <code>{@link #logprobs}</code> is set, <code>completion</code> will be automatically added into <code>{@link #expand}</code> to get the logprobs.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-logprobs">Online Docs</a>
	 */
	Integer logprobs;
	
	/**
	 * <p>The maximum number of examples to be ranked by <a href="https://beta.openai.com/docs/api-reference/searches/create">Search</a> when using <code>{@link #file}</code>. Setting it to a higher value leads to improved accuracy but with increased latency and cost.</p>
	 * <p>
	 * <code>Defaults to 200</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-max_examples">Online Docs</a>
	 */
	Integer maxExamples;
	
	/**
	 * <p>Modify the likelihood of specified tokens appearing in the completion.</p>
	 * <p>Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this <a href="https://beta.openai.com/tokenizer?view=bpe">tokenizer tool</a> (which works for both GPT-2 and GPT-3) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.</p>
	 * <p>As an example, you can pass <code>{"50256": -100}</code> to prevent the &lt;|endoftext|&gt; token from being generated.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-logit_bias">Online Docs</a>
	 */
	Map logitBias;
	
	/**
	 * <p>If set to <code>true</code>, the returned JSON will include a "prompt" field containing the final prompt that was used to request a completion. This is mainly useful for debugging purposes.</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-return_prompt">Online Docs</a>
	 */
	Boolean returnPrompt;
	
	/**
	 * <p>A special boolean flag for showing metadata. If set to <code>true</code>, each document entry in the returned JSON will contain a "metadata" field.</p>
	 * <p>This flag only takes effect when <code>{@link #file}</code> is set.</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-return_metadata">Online Docs</a>
	 */
	Boolean returnMetadata;
	
	/**
	 * <p>If an object name is in the list, we provide the full information of the object; otherwise, we only provide the object ID. Currently we support <code>completion</code> and <code>{@link #file}</code> objects for expansion.</p>
	 * <p>
	 * <code>Defaults to []</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/classifications/create#classifications/create-expand">Online Docs</a>
	 */
	List<String> expand;
	
}