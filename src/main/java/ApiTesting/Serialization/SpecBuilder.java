package ApiTesting.Serialization;

import ApiTesting.Pojo.AddPlace;
import ApiTesting.Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder {

    public static void main(String[] args) {

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

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        RequestSpecification response = given().spec(req)
                .body(p);

        ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        Response res = response.when().post("maps/api/place/add/json")
        .then().spec(resp).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);

    }
}
