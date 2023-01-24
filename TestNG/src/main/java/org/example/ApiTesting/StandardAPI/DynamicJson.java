package org.example.ApiTesting.StandardAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.Addbook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).log().all()
                .extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String id = js.get("ID");
        System.out.println(id);

    }

    @DataProvider(name="BooksData")
    public Object[][] getData() {
        return new Object[][]{
                {"awfawfdwa","2255"},{"awfafsawwfdwa","225455"},{"awfttwawfdwa","227855"}
        };
    }
}
