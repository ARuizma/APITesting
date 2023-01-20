package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) {

        //Validate if Add place API is working

        //Given - All input details
        //When - Submit the API -resource, http method
        //Then - Validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";
        RestAssured.useRelaxedHTTPSValidation();
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response); //For parsing Json
        String placeID = js.getString("place_id");

        System.out.println(placeID);

        //Add place -> Update Place with New Address -> Get Place to validate if New address is present
    }
}