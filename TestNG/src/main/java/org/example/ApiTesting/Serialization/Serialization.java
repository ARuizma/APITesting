package org.example.ApiTesting.Serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.ApiTesting.Pojo.AddPlace;
import org.example.ApiTesting.Pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialization {

    public static void main(String[] args) {

        RestAssured.baseURI="https://rahulshettyacademy.com";
        RestAssured.useRelaxedHTTPSValidation();

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("my address");
        p.setLanguage("Spanish");
        p.setPhone_number("2222222");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("My name");

        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("park");
        p.setTypes(myList);

        Location l = new Location();
        l.setLat(-3.87454);
        l.setlng(35.4778);
        p.setLocation(l);

        Response response = given().queryParam("key", "qaclick123")
                .body(p)
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);

    }
}
