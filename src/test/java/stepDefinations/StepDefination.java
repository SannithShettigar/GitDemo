package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Addplace;
import pojo.Location;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utilities;

public class StepDefination extends Utilities {
	
	RequestSpecification response;
	ResponseSpecification responseSpec;
	Response response1;
	TestDataBuild data=new TestDataBuild();
	static String placeId;
	
	@Given("Add Place  Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
	
		
		/*RestAssured.baseURI="https://rahulshettyacademy.com";
		RequestSpecification requestSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();*/
		// responseSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		response=given().spec(requestSpecification())
		
				 //.body(TestDataBuild.addPlacePayload());
		.body(data.addPlacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//constructor will be called with value of resource which you pass 
		ApiResources resourceApi=ApiResources.valueOf(resource);
		System.out.println(resourceApi.getResource());;
		
		if(method.equalsIgnoreCase("POST"))
		 response1=response.when().post(resourceApi.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 response1=response.when().get(resourceApi.getResource());
				//.then().spec(responseSpec).extract().response();
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    assertEquals(response1.getStatusCode(), 200);
		
	
	}
	@Then("{string} in resposne body is {string}")
	public void in_resposne_body_is(String keyValue, String expectedValue) {
	    
		//String res=response1.asString();
		// js=new JsonPath(res);
		assertEquals(getJsonPath(response1,keyValue),expectedValue);
	}
	
	@Then("verify palce_id created maps to {string} using {string}")
	public void verify_palce_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    
		 placeId=getJsonPath(response1,"place_id");
		response=given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String actualname=getJsonPath(response1,"name");
		assertEquals(actualname,expectedname);
	
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		response=given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

	
	

	


}
