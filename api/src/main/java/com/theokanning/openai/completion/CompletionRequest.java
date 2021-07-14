package com.theokanning.openai.completion;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Creates a new completion for the provided prompt and parameters</p>
 *
 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create">Online Docs</a>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompletionRequest {
	
	/**
	 * <p>The ID of the engine to use for this request</p>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-engine_id">Online Docs</a>
	 */
	@NonNull
	String engineId;
	
	/**
	 * <p>The prompt(s) to generate completions for, encoded as a string, a list of strings, or a list of token lists.</p>
	 * <p>Note that &lt;|endoftext|&gt; is the document separator that the model sees during training, so if a prompt is not specified the model will generate as if from the beginning of a new document.</p>
	 * <p>
	 * <code>Defaults to &lt;|endoftext|&gt;</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-prompt">Online Docs</a>
	 */
	@Singular("prompt")
	List<String> prompt;
	
	/**
	 * <p>The maximum number of tokens to generate. Requests can use up to 2048 tokens shared between prompt and completion. (One token is roughly 4 characters for normal English text)</p>
	 * <p>
	 * <code>Defaults to 16</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-max_tokens">Online Docs</a>
	 */
	Integer maxTokens;
	
	/**
	 * <p>What <a href="https://towardsdatascience.com/how-to-sample-from-language-models-682bceb97277" target="_blank" rel="noopener noreferrer">sampling temperature</a> to use. Higher values means the model will take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.</p>
	 * <p>We generally recommend altering this or <code>{@link #topP}</code> but not both.</p>
	 * <p>
	 * <code>Defaults to 1</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-temperature">Online Docs</a>
	 */
	Double temperature;
	
	/**
	 * <p>An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.</p>
	 * <p>We generally recommend altering this or <code>{@link #temperature}</code> but not both.</p>
	 * <p>
	 * <code>Defaults to 1</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-top_p">Online Docs</a>
	 */
	Double topP;
	
	/**
	 * <p>How many completions to generate for each prompt.</p>
	 * <p><strong>Note:</strong> Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for <code>{@link #maxTokens}</code> and <code>{@link #stop}</code>.</p>
	 * <p>
	 * <code>Defaults to 1</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-n">Online Docs</a>
	 */
	Integer n;
	
	/**
	 * <p>Include the log probabilities on the <code>{@link #logprobs}</code> most likely tokens, as well the chosen tokens. For example, if <code>{@link #logprobs}</code> is 10, the API will return a list of the 10 most likely tokens. the API will always return the <code>logprob</code> of the sampled token, so there may be up to <code>logprobs+1</code> elements in the response.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-logprobs">Online Docs</a>
	 */
	Integer logprobs;
	
	/**
	 * <p>Echo back the prompt in addition to the completion</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-echo">Online Docs</a>
	 */
	Boolean echo;
	
	/**
	 * <p>Up to 4 sequences where the API will stop generating further tokens. The returned text will not contain the stop sequence.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-stop">Online Docs</a>
	 */
	@Singular("stop")
	List<String> stop;
	
	/**
	 * <p>Number between 0 and 1 that penalizes new tokens based on whether they appear in the text so far. Increases the model's likelihood to talk about new topics.</p>
	 * <p><a href="https://beta.openai.com/docs/api-reference/parameter-details">See more information about frequency and presence penalties.</a>.</p>
	 * <p>
	 * <code>Defaults to 0</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-presence_penalty">Online Docs</a>
	 */
	Double presencePenalty;
	
	/**
	 * <p>Number between 0 and 1 that penalizes new tokens based on their existing frequency in the text so far. Decreases the model's likelihood to repeat the same line verbatim.</p>
	 * <p><a href="https://beta.openai.com/docs/api-reference/parameter-details">See more information about frequency and presence penalties.</a>.</p>
	 * <p>
	 * <code>Defaults to 0</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-frequency_penalty">Online Docs</a>
	 */
	Double frequencyPenalty;
	
	/**
	 * <p>Generates <code>{@link #bestOf}</code> completions server-side and returns the "best" (the one with the lowest log probability per token). Results cannot be streamed.</p>
	 * <p>When used with <code>{@link #n}</code>, <code>{@link #bestOf}</code> controls the number of candidate completions and <code>{@link #n}</code> specifies how many to return – <code>{@link #bestOf}</code> must be greater than <code>{@link #n}</code>.</p>
	 * <p><strong>Note:</strong> Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for <code>{@link #maxTokens}</code> and <code>{@link #stop}</code>.</p>
	 * <p>
	 * <code>Defaults to 1</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-best_of">Online Docs</a>
	 */
	Integer bestOf;
	
	/**
	 * <p>Modify the likelihood of specified tokens appearing in the completion.</p>
	 * <p>Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this <a href="https://beta.openai.com/tokenizer?view=bpe">tokenizer tool</a> (which works for both GPT-2 and GPT-3) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.</p>
	 * <p>As an example, you can pass <code>{"50256": -100}</code> to prevent the &lt;|endoftext|&gt; token from being generated.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 *
	 * @see <a href="https://beta.openai.com/docs/api-reference/completions/create#completions/create-logit_bias">Online Docs</a>
	 */
	@Singular("logitBias")
	Map<String, Integer> logitBias;
	
}
