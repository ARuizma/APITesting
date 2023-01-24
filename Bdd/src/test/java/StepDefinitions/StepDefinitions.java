package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.ApiTesting.Pojo.AddPlace;
import org.example.ApiTesting.Pojo.Location;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class StepDefinitions {

    RequestSpecification res;
    ResponseSpecification resp;
    Response response;
    RequestSpecification req;

    @Given("user adds place payload")
    public void add_place_payload(){
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

        req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        res = given().spec(req).body(p);
        
    }

    @When("user calls {string} with Post request")
    public void call_request(String path){
        response = res.when().post("maps/api/place/add/json")
                .then().spec(resp).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void response_status_success(int int1){

        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void response_body_result(String key, String value){
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(key).toString(), value);
    }
}
