package org.example.ApiTesting.StandardAPI;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    public static JsonPath rawToJson(String response) {
        JsonPath jsg = new JsonPath(response);
        return jsg;
    }
}
