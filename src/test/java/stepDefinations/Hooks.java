package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//Write code that will give place id
		//execute this code only place id is null
		StepDefination sd=new StepDefination();
		if(sd.placeId==null)
		sd.add_place_payload("Shettigar", "English", "Asia");
		sd.user_calls_with_http_request("addPlaceApi", "POST");
		sd.verify_palce_id_created_maps_to_using("Shettigar", "getPlaceApi");
	}

}
