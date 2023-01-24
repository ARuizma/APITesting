package StepDefinitions;

import io.cucumber.java.en.And;
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
import resources.EnumResources;
import resources.TestDataBuild;
import resources.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.testng.AssertJUnit.assertEquals;

public class StepDefinitions extends utils {

    RequestSpecification res;
    ResponseSpecification resp;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("user adds place payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} request")
    public void call_request(String path, String request){
        EnumResources resourceAPI = EnumResources.valueOf(path);
        resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(request.equalsIgnoreCase("POST")){
            response = res.when().post(resourceAPI.getResource());
        } else if (request.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }
                            //.then().spec(resp).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void response_status_success(int int1){

        assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    public void response_body_result(String key, String value){
        assertEquals(getJsonPath(response, key), value);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedname, String path) throws IOException {
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        call_request(path,"GET");
        String actualname = getJsonPath(response, "name");
        assertEquals(actualname,expectedname);
    }

    @Given("user creates DeletePlace Payload")
    public void create_deleteplace_payload() throws IOException {

        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
