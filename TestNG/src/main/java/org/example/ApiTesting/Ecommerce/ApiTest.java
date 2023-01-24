package org.example.ApiTesting.Ecommerce;

import ApiTesting.Pojo.LoginRequest;
import ApiTesting.Pojo.LoginResponse;
import ApiTesting.Pojo.OrderDetail;
import ApiTesting.Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.json.Json;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {

    public static void main(String[] args) {

        //Login
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build().relaxedHTTPSValidation();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("saifer_spirit@hotmail.es");
        loginRequest.setUserPassword("Apitesting*1");
        RequestSpecification reqlogin = given().spec(req).body(loginRequest);

        LoginResponse loginResponse = reqlogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

        System.out.println(loginResponse.getToken());
        String token = loginResponse.getToken();
        System.out.println(loginResponse.getUserId());
        String userId = loginResponse.getUserId();

        //Create Product
        RequestSpecification addp = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token)
                .build().relaxedHTTPSValidation();

        RequestSpecification reqaddp = given().log().all().spec(addp).param("productName","Tests")
                .param("productAddedBy",userId)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","1500")
                .param("productDescription","Custom product")
                .param("productFor","Men")
                .multiPart("productImage",new File("C:\\Users\\ARuizMarin\\Pictures\\My Pictures\\shoes.jpg"));

        String addpresp = reqaddp.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(addpresp);
        String productId = js.get("productId");

        //Create Order
        RequestSpecification createord = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token)
                .setContentType(ContentType.JSON).build().relaxedHTTPSValidation();

        OrderDetail orderdetails = new OrderDetail();
        orderdetails.setCountry("Spain");
        orderdetails.setProductOrderedId(productId);

        List<OrderDetail> orderDetailList= new ArrayList<OrderDetail>();
        orderDetailList.add(orderdetails);

        Orders orders = new Orders();
        orders.setOrders(orderDetailList);

        RequestSpecification reqcreateord = given().log().all().spec(createord).body(orders);

        String respcreateord = reqcreateord.when().post("/api/ecom/order/create-order")
                .then().log().all().extract().response().asString();

        System.out.println(respcreateord);

        //Delete Product
        RequestSpecification delprodreq = given().log().all().spec(createord).pathParam("productId",productId);
        String RespDel = delprodreq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();

        JsonPath jsdel = new JsonPath(RespDel);

        Assert.assertEquals("Product Deleted Successfully",jsdel.get("message"));

    }
}
