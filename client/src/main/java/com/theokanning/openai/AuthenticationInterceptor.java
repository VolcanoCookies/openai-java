package com.theokanning.openai;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * OkHttp Interceptor that adds an authorization token header, and organization header if not null.
 */
public class AuthenticationInterceptor implements Interceptor {
	
	private final String token;
	
	private final String organization;
	
	AuthenticationInterceptor(String token, String organization) {
		this.token = token;
		this.organization = organization;
	}
	
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request.Builder requestBuilder = chain.request()
				.newBuilder()
				.header("Authorization", "Bearer " + token);
		if (organization != null) {
			requestBuilder.header("OpenAI-Organization", organization);
		}
		return chain.proceed(requestBuilder.build());
	}
}
