package com.theokanning.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.theokanning.openai.answer.AnswerRequest;
import com.theokanning.openai.answer.AnswerResult;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.engine.Engine;
import com.theokanning.openai.search.SearchRequest;
import com.theokanning.openai.search.SearchResult;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenAiService {
	
	OpenAiApi api;
	
	public final FileService fileService;
	
	/**
	 * You can find your api keys at <a href="https://beta.openai.com/account/api-keys">openai.com</a>
	 *
	 * @param token Your OpenAi token.
	 */
	public OpenAiService(String token) {
		this(token, null);
	}
	
	/**
	 * You can find your api keys at <a href="https://beta.openai.com/account/api-keys">openai.com</a>
	 *
	 * @param token        Your OpenAi token.
	 * @param organization Optional organization ID if you are part of multiple organizations.
	 */
	public OpenAiService(String token, String organization) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(new AuthenticationInterceptor(token, organization))
				.connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
				.build();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.openai.com/")
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
		
		api = retrofit.create(OpenAiApi.class);
		
		fileService = new FileService(api);
		
	}
	
	/**
	 * @see Engine
	 */
	public List<Engine> getEngines() {
		return api.getEngines().blockingGet().data;
	}
	
	/**
	 * @see Engine
	 */
	public Engine getEngine(String engineId) {
		return api.getEngine(engineId).blockingGet();
	}
	
	/**
	 * @see CompletionRequest
	 * @see CompletionResult
	 */
	public CompletionResult createCompletion(String engineId, CompletionRequest request) {
		return api.createCompletion(engineId, request).blockingGet();
	}
	
	/**
	 * @see SearchRequest
	 * @see SearchResult
	 */
	public List<SearchResult> search(String engineId, SearchRequest request) {
		return api.search(engineId, request).blockingGet().data;
	}
	
	/**
	 * @see AnswerRequest
	 * @see AnswerResult
	 */
	public AnswerResult answer(AnswerRequest request) {
		return api.answer(request).blockingGet();
	}
	
}