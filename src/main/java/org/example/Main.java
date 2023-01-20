package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

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
        JsonPath js = ReusableMethods.rawToJson(response); //For parsing Json
        String placeID = js.getString("place_id");
        System.out.println(placeID);

        //UpdatePlace
        String newAddress = "My Custom Place, Wowowo";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

        //Get Place

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeID)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath jsg = ReusableMethods.rawToJson(getPlaceResponse);
        String addressID = jsg.getString("address");
        System.out.println(addressID);

        //Cucumber Junit, Testng
        //Testng
        Assert.assertEquals(addressID, newAddress);
    }
}