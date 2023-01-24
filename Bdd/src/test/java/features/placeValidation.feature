Feature: Validating Place API's

  Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
    Given user adds place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with Post request
    Then the API call is success with status code 200
    Then "status" in response body is "OK"

    Examples:
    |name|language|address|
    |something|Spanish|somestreet|
    |otherthing|English|otherstreet|
