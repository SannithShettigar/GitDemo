package resources;

import io.cucumber.messages.internal.com.google.common.io.Resources;

public enum ApiResources {
	
	addPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	
	private String resource;
	
	ApiResources(String resource)
	{
		this.resource=resource;
	}
	
	
	public String getResource()
	{
	
		return resource;
	}
}
