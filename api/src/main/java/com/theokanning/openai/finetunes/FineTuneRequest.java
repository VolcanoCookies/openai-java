package com.theokanning.openai.finetunes;

import lombok.*;

import java.util.List;

/**
 * <p>Creates a job that fine-tunes a specified model from a given dataset.</p>
 * <p>Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.</p>
 * <p><a href="https://beta.openai.com/docs/guides/fine-tuning">Learn more about Fine-tuning</a></p>
 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create">Online Docs</a>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FineTuneRequest {
	
	/**
	 * <p>The ID of an uploaded file that contains training data.</p>
	 * <p>See <a href="https://beta.openai.com/docs/api-reference/files/upload">upload file</a> for how to upload a file.</p>
	 * <p>Your dataset must be formatted as a JSONL file, where each training example is a JSON object with the keys "prompt" and "completion". Additionally, you must upload your file with the purpose <code>fine-tune</code>.</p>
	 * <p>See the <a href="https://beta.openai.com/docs/guides/fine-tuning/creating-training-data">fine-tuning guide</a> for more details.</p>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-training_file">Online Docs</a>
	 */
	@NonNull
	String trainingFile;
	
	/**
	 * <p>The ID of an uploaded file that contains validation data.</p>
	 * <p>If you provide this file, the data is used to generate validation metrics periodically during fine-tuning. These metrics can be viewed in the <a href="https://beta.openai.com/docs/guides/fine-tuning/analyzing-your-fine-tuned-model">fine-tuning results file</a>. Your train and validation data should be mutually exclusive.</p>
	 * <p>Your dataset must be formatted as a JSONL file, where each validation example is a JSON object with the keys "prompt" and "completion". Additionally, you must upload your file with the purpose <code>fine-tune</code>.</p>
	 * <p>See the <a href="https://beta.openai.com/docs/guides/fine-tuning/creating-training-data">fine-tuning guide</a> for more details.</p>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-validation_file">Online Docs</a>
	 */
	String validationFile;
	
	/**
	 * <p>The name of the base model to fine-tune. You can select one of "ada", "babbage", or "curie". To learn more about these models, see the <a href="https://beta.openai.com/docs/engines" target="_blank" rel="noopener noreferrer">Engines</a> documentation.</p>
	 * <p>
	 * <code>Defaults to curie</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-model">Online Docs</a>
	 */
	String model;
	
	/**
	 * <p>The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.</p>
	 * <p>
	 * <code>Defaults to 4</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-n_epochs">Online Docs</a>
	 */
	Integer nEpochs;
	
	/**
	 * <p>The batch size to use for training. The batch size is the number of training examples used to train a single forward and backward pass.</p>
	 * <p>In general, we've found that larger batch sizes tend to work better for larger datasets. You may want to experiment with several values to see what produces the best results.</p>
	 * <p>
	 * <code>Defaults to 4</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-batch_size">Online Docs</a>
	 */
	Integer batchSize;
	
	/**
	 * <p>The learning rate multiplier to use for training. The fine-tuning learning rate is determined by the original learning rate used for pretraining multiplied by this value.</p>
	 * <p>You may want to experiment with several values to see what produces the best results. We recommend values in the range from 0.01-0.4.</p>
	 * <p>When using smaller learning rates, we often recommend training for longer (more epochs). This is likely to lead to better performance.</p>
	 * <p>Additionally, larger learning rates often play well with larger batch sizes, and smaller learning rates with smaller batch sizes.</p>
	 * <p>
	 * <code>Defaults to 0.1</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-learning_rate_multiplier">Online Docs</a>
	 */
	Double learningRateMultiplier;
	
	/**
	 * <p>On classification tasks, we recommend setting this to <code>false</code>.</p>
	 * <p>On all other tasks, we recommend setting this to <code>true</code>. When <code>true</code>, we pack as many prompt-completion pairs as possible into each training example. This greatly increases the speed of a fine-tuning job, often without negatively affecting model performance.</p>
	 * <p>In particular, with packing, each example in a training batch takes the form <code>&lt;prompt1&gt;&lt;completion1&gt;&lt;end_token&gt;&lt;prompt2&gt;&lt;completion2&gt;&lt;end_token&gt;...</code>. Without packing, each example contains a single prompt-completion pair.</p>
	 * <p>
	 * <code>Defaults to true</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-use_packing">Online Docs</a>
	 */
	Boolean usePacking;
	
	/**
	 * <p>The weight to use for the prompt loss. The optimum value here depends on your use case. This determines how much the model prioritizes learning from prompt tokens vs learning from completion tokens.</p>
	 * <p>For generative (e.g. chat) use cases, or tasks where the prompts and completions are of similar length and importance, a loss weight of 1 is recommended.</p>
	 * <p>For classification tasks, we recommend setting prompt_loss_weight to 0.1. (Since it's often easier to ask a question than to answer it, if we have a large prompt loss weight on classification tasks, the model can "cheat" by learning to predict (easier) prompt tokens, instead of learning to predict the correct answer.)</p>
	 * <p>In general, we recommend trying the values 0, 0.1, and 1, depending on your use case and the importance of learning to predict the prompt tokens.</p>
	 * <p>
	 * <code>Defaults to 0.1</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-prompt_loss_weight">Online Docs</a>
	 */
	Double promptLossWeight;
	
	/**
	 * <p>If set, we calculate classification-specific metrics such as accuracy and F-1 score using the validation set at the end of every epoch. These metrics can be viewed in the <a href="https://beta.openai.com/docs/guides/fine-tuning/analyzing-your-fine-tuned-model">results file</a>. </p>
	 * <p>In order to compute classification metrics, you must provide a <code>validation_file</code>. Additionally, you must specify <code>classification_n_classes</code> for multiclass classification or <code>classification_positive_class</code> for binary classification.</p>
	 * <p>
	 * <code>Defaults to false</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-compute_classification_metrics">Online Docs</a>
	 */
	Boolean computeClassificationMetrics;
	
	/**
	 * <p>The number of classes in a classification task.</p>
	 * <p>This parameter is required for multiclass classification.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-classification_n_classes">Online Docs</a>
	 */
	Integer classificationNClasses;
	
	/**
	 * <p>The positive class in binary classification.</p>
	 * <p>This parameter is needed to generate precision, recall, and F1 metrics when doing binary classification.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-classification_positive_class">Online Docs</a>
	 */
	String classificationPositiveClass;
	
	/**
	 * <p>If this is provided, we calculate F-beta scores at the specified beta values. The F-beta score is a generalization of F-1 score. This is only used for binary classification.</p>
	 * <p>With a beta of 1 (i.e. the F-1 score), precision and recall are given the same weight. A larger beta score puts more weight on recall and less on precision. A smaller beta score puts more weight on precision and less on recall.</p>
	 * <p>
	 * <code>Defaults to null</code>
	 * <p>
	 * <a href="https://beta.openai.com/docs/api-reference/fine-tunes/create#fine-tunes/create-classification_betas">Online Docs</a>
	 */
	List<String> classificationBetas;
	
}
