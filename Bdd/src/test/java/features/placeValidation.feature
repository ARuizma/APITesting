Feature: Validating Place API's

@AddPlace
  Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
    Given user adds place payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "Post" request
    Then the API call is success with status code 200
    Then "status" in response body is "OK"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
    |name|language|address|
    |something|Spanish|somestreet|
    |otherthing|English|otherstreet|

@DeletePlace
    Scenario: Verify if Delete Place works

      Given user creates DeletePlace Payload
      When user calls "deletePlaceAPI" with "Post" request
      Then the API call is success with status code 200
      Then "status" in response body is "OK"