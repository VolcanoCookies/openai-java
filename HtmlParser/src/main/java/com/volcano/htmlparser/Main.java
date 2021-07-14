package com.volcano.htmlparser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Document doc = Jsoup.parse(new File("E:\\Collective\\openai-java\\HtmlParser\\src\\main\\java\\com\\volcano\\htmlparser\\apiref.html"), Charset.defaultCharset().name());
		
		Elements endpoints = doc.getElementsByClass("section endpoint");
		
		List<String> classes = new ArrayList<>();
		
		for (Element endpoint : endpoints) {
			
			StringBuilder builder = new StringBuilder();
			
			String name = endpoint.getElementsByAttribute("name").first().attr("name");
			String properName = Arrays.stream(name.split("[^A-z]")).map(StringUtils::capitalize).collect(Collectors.joining());
			String summary = endpoint.getElementsByClass("endpoint-summary").first().html();
			summary += "\n@see <a href=\"https://beta.openai.com/docs/api-reference/" + name + "\">Online Docs</a>";
			
			Elements params = endpoint.getElementsByClass("param-row");
			
			builder.append("==================================================\n");
			builder.append("/**\n* ").append(summary.replaceAll("\n", "\n* ")).append("\n*/\n");
			builder.append("@Builder\n" +
					"@NoArgsConstructor\n" +
					"@AllArgsConstructor\n" +
					"@Data\n");
			builder.append("public class ").append(properName).append("Request {\n\n");
			
			List<String> paramNames = new ArrayList<>();
			
			for (Element param : params) {
				
				String anchorLink = param.getElementsByClass("api-ref-anchor-link").first().attr("href");
				String paramName = param.getElementsByClass("param-name").first().html();
				paramNames.add(paramName);
				paramName = Arrays.stream(paramName.split("_"))
						.map(StringUtils::capitalize)
						.collect(Collectors.joining());
				paramName = StringUtils.uncapitalize(paramName);
				String paramType = param.getElementsByClass("param-type").first().html();
				if (paramType.equals("array")) {
					paramType = "List<String>";
				}
				if (paramType.equals("number")) {
					paramType = "double";
				}
				Boolean paramRequired = !param.getElementsByClass("param-reqd").isEmpty();
				
				String description = param.getElementsByClass("markdown-content").first().html();
				
				Element def = param.getElementsByClass("param-default").first();
				
				if (def != null) {
					description += "\n<p>\n<code>" + def.html() + "</code>";
				}
				description += "\n<p>\n@see <a href=\"https://beta.openai.com/docs/api-reference/" + name + anchorLink + "\">Online Docs</a>";
				
				builder.append("/**\n* ");
				builder.append(description.replaceAll("\n", "\n * ")).append("\n*/");
				if (paramRequired) {
					builder.append("\n@NonNull");
				}
				builder.append("\n").append(StringUtils.capitalize(paramType)).append(" ").append(paramName).append(";\n\n");
				
			}
			
			builder.append("\n}");
			
			String res = builder.toString();
			res = res.replaceAll("\n\n+", "\n\n");
			res = res.replaceAll("href=\"[^(https)]", "href=\"https://beta.openai.com/");
			for (String p : paramNames) {
				res = res.replaceAll("<code>" + p + "</code>", "<code>{@link #" + StringUtils.uncapitalize(Arrays.stream(p.split("_")).map(StringUtils::capitalize).collect(Collectors.joining())) + "}</code>");
			}
			classes.add(res);
			
		}
		
		for (String aClass : classes) {
			if (aClass.contains("nswers the specified quest")) {
				System.out.println(aClass);
			}
		}
		
	}
	
}
