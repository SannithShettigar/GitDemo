package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {
	
	public static  RequestSpecification requestSpec;
	
	public RequestSpecification requestSpecification() throws IOException
	{
       if(requestSpec==null)
       {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
	//RestAssured.baseURI="https://rahulshettyacademy.com";
	 requestSpec=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
			 .addFilter(RequestLoggingFilter.logRequestTo(log))
			 .addFilter(ResponseLoggingFilter.logResponseTo(log))
	.setContentType(ContentType.JSON).build();
	 return requestSpec;
       }
       return requestSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Sannith\\eclipse-workspace\\RESTApiFramework\\src\\test\\java\\resources\\global.porperties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}

	
	public String getJsonPath(Response response1, String key )
	{
		
		String res=response1.asString();
		JsonPath js=new JsonPath(res);
		return js.get(key).toString();
	}
}
