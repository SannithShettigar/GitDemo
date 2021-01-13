package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestDataBuild {
	
	public static Addplace addPlacePayload(String name, String language, String address)
	{
		
		Addplace ap=new Addplace();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("545454");
		ap.setWebsite("https://rahulshettyacademy.com");
		
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		ap.setLocation(loc);
		return ap;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n\r\n       \"place_id\":\""+placeId+"\"\r\n\r\n}";
	}

}
