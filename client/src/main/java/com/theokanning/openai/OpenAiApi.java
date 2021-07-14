package com.theokanning.openai;

import com.theokanning.openai.answer.AnswerRequest;
import com.theokanning.openai.answer.AnswerResult;
import com.theokanning.openai.classification.ClassificationRequest;
import com.theokanning.openai.classification.ClassificationResult;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.engine.Engine;
import com.theokanning.openai.files.DeleteResult;
import com.theokanning.openai.files.FileResult;
import com.theokanning.openai.finetunes.FineTuneEvent;
import com.theokanning.openai.finetunes.FineTuneRequest;
import com.theokanning.openai.finetunes.FineTuneResult;
import com.theokanning.openai.search.SearchRequest;
import com.theokanning.openai.search.SearchResult;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;

public interface OpenAiApi {
	
	@GET("v1/engines")
	Single<OpenAiResponse<Engine>> getEngines();
	
	@GET("/v1/engines/{engine_id}")
	Single<Engine> getEngine(@Path("engine_id") String engineId);
	
	@POST("/v1/engines/{engine_id}/completions")
	Single<CompletionResult> createCompletion(@Path("engine_id") String engineId, @Body CompletionRequest request);
	
	@POST("/v1/engines/{engine_id}/search")
	Single<OpenAiResponse<SearchResult>> search(@Path("engine_id") String engineId, @Body SearchRequest request);
	
	@POST("/v1/answers")
	Single<AnswerResult> answer(@Body AnswerRequest request);
	
	@Multipart
	@POST("/v1/files")
	Single<FileResult> uploadFile(
			@Part("purpose") RequestBody purpose,
			@Part MultipartBody.Part file
	);
	
	@GET("/v1/files/{file_id}")
	Single<FileResult> getFile(@Path("file_id") String fileId);
	
	@GET("/v1/files")
	Single<OpenAiResponse<FileResult>> listFiles();
	
	@DELETE("/v1/files/{file_id}")
	Single<DeleteResult> deleteFile(@Path("file_id") String fileId);
	
	@POST("/v1/classifications")
	Single<ClassificationResult> classification(@Body ClassificationRequest request);
	
	@POST("/v1/fine-tunes")
	Single<FineTuneResult> fineTune(@Body FineTuneRequest request);
	
	@GET("/v1/fine-tunes")
	Single<OpenAiResponse<FineTuneResult>> listFineTunes();
	
	@GET("/v1/fine-tunes/{fine_tune_id}")
	Single<FineTuneResult> getFineTune(@Path("fine_tune_id") String fineTuneId);
	
	@POST("/v1/fine-tunes/{fine_tune_id}/cancel")
	Single<FineTuneResult> cancelFineTune(@Path("fine_tune_id") String fineTuneId);
	
	@POST("/v1/fine-tunes/{fine_tune_id}/events")
	Single<OpenAiResponse<FineTuneEvent>> listFineTuneEvents(@Path("fine_tune_id") String fineTuneId);
	
}
