Feature: Validating Place API's

  Scenario: Verify if Place is being Succesfully added using AddPlaceAPI
    Given user adds place payload
    When user calls "AddPlaceAPI" with Post request
    Then the API call is success with status code 200
    Then "status" in response body is "OK"