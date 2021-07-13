package com.theokanning.openai;

import com.theokanning.openai.files.DeleteResult;
import com.theokanning.openai.files.FileResult;
import com.theokanning.openai.files.Purpose;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;

public class FileService {
	
	OpenAiApi api;
	
	public FileService(OpenAiApi api) {
		this.api = api;
	}
	
	public FileResult uploadFile(File file, Purpose purpose) {
		RequestBody body = RequestBody.create(
				MediaType.parse("multipart/form-data"),
				file
		);
		
		MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", file.getName(), body);
		
		RequestBody purposeBody = RequestBody.create(MultipartBody.FORM, purpose.name);
		
		return api.uploadFile(purposeBody, fileBody).blockingGet();
	}
	
	public FileResult uploadFile(String fileContent, String fileName, Purpose purpose) {
		RequestBody body = RequestBody.create(
				MediaType.parse("multipart/form-data"),
				fileContent
		);
		
		MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", fileName, body);
		
		RequestBody purposeBody = RequestBody.create(MultipartBody.FORM, purpose.name);
		
		return api.uploadFile(purposeBody, fileBody).blockingGet();
	}
	
	public FileResult retrieveFile(String fileId) {
		return api.retrieveFile(fileId).blockingGet();
	}
	
	public List<FileResult> listFiles() {
		return api.listFiles().blockingGet().data;
	}
	
	public DeleteResult deleteFile(String fileId) {
		return api.deleteFile(fileId).blockingGet();
	}
	
}
