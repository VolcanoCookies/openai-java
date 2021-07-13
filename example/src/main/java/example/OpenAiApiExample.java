package example;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.answer.AnswerRequest;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.engine.Engine;
import com.theokanning.openai.files.FileResult;
import com.theokanning.openai.files.Purpose;
import com.theokanning.openai.search.SearchRequest;

import java.io.File;
import java.util.Arrays;
import java.util.List;

class OpenAiApiExample {
	
	public static void main(String... args) {
		String token = System.getenv("OPENAI_TOKEN");
		OpenAiService service = new OpenAiService(token);
		
		System.out.println("\nGetting available engines...");
		service.getEngines().forEach(System.out::println);
		
		System.out.println("\nGetting ada engine...");
		Engine ada = service.getEngine("ada");
		System.out.println(ada);
		
		System.out.println("\nCreating completion...");
		
		CompletionRequest completionRequest = CompletionRequest.builder()
				.prompt("Somebody once told me the world is gonna roll me")
				.echo(true)
				.build();
		service.createCompletion("ada", completionRequest).getChoices().forEach(System.out::println);
		
		System.out.println("\nSearching documents...");
		SearchRequest searchRequest = SearchRequest.builder()
				.documents(Arrays.asList("Water", "Earth", "Electricity", "Fire"))
				.query("Pikachu")
				.build();
		service.search("ada", searchRequest).forEach(System.out::println);
		
		System.out.println("\nRequesting answer...");
		AnswerRequest answerRequest = AnswerRequest.builder()
				.documents(Arrays.asList("Shrek lives in a swamp", "Shrek has a friend which is a donkey"))
				.examples(List.of(List.of("Where does Donald Duck live", "Donald Duck lives in Duckburg"),
						List.of("Name one friend of Donald Duck", "One of Donald Ducks friends is Mickey Mouse")))
				.examplesContext("Donald Duck lives in the state of Calisota, in a city called Duckburg. Donald has many friends such as Mickey Mouse and Goofy.")
				.searchModel("ada")
				.model("curie")
				.question("What friend does Shrek have?")
				.build();
		service.answer(answerRequest).getAnswers().forEach(System.out::println);
		
		System.out.println("\nUploading a file...");
		File file = new File("C:\\Users\\frane\\Desktop\\openai\\collective.jsonl");
		FileResult result = service.fileService.uploadFile(file, Purpose.ANSWERS);
		System.out.println(result.id + " : " + result.filename + " : " + result.bytes);
		
		System.out.println("\nListing files...");
		List<FileResult> list = service.fileService.listFiles();
		list.forEach(System.out::println);
		
		System.out.println("\nRetrieving file...");
		FileResult retrieved = service.fileService.retrieveFile(result.id);
		System.out.println(retrieved.id + " : " + retrieved.filename + " : " + retrieved.bytes);
		
		System.out.println("\nWaiting 5 seconds for file to be processed...");
		long start = System.nanoTime();
		while (System.nanoTime() - start < 5000000000L) {
		}
		
		System.out.println("\nDeleting file...");
		System.out.println(service.fileService.deleteFile(result.id).id);
		
		System.out.println("\nListing files...");
		list = service.fileService.listFiles();
		list.forEach(System.out::println);
		
	}
}