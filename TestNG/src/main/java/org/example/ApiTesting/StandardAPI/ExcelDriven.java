package org.example.ApiTesting.StandardAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.dataDriven;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ExcelDriven {

    @Test//(dataProvider = "BooksData")
    public void addBook() throws IOException {

        dataDriven d=new dataDriven();
        ArrayList<String> data= d.getData("sample","RestAddBook");

        HashMap<String, Object> map = new HashMap<>();
        map.put("name",data.get(1));
        map.put("isbn",data.get(2));
        map.put("aisle",data.get(3));
        map.put("author",data.get(4));

        /*HashMap<String, Object> map2 = new HashMap<>();
        map2.put("lat","-34.25858");
        map2.put("lng","34.25858");
        map.put("location",map2);*/

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body(map)
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
