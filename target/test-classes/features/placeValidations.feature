Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
Given Add Place  Payload "<name>" "<language>" "<address>"
When user calls "addPlaceApi" with "POST" http request
Then the API call is success with status code 200
And "status" in resposne body is "OK"
And "scope" in resposne body is "APP"
And verify palce_id created maps to "<name>" using "getPlaceApi"


Examples:
   |name    | language      | address |
   |SandP   |Tulu           |mumbai   |
#  |PandS   |Malayalam      |mumbai   |


@DeletePlace
Scenario: Verify the Delete API functionality working 
Given DeletePlace Payload
When user calls "deletePlaceApi" with "Post" http request
Then the API call is success with status code 200
And "status" in resposne body is "OK"