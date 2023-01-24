package org.example.ApiTesting.JIRAAPI;
import ApiTesting.StandardAPI.*;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraTest {

    public static void main (String[] args) {

        RestAssured.baseURI="http://localhost:8080";

        //Login
        SessionFilter session = new SessionFilter();
        given().header("Content-Type","application/json")
                        .body("{ \"username\": \"andresrumarin\", \"password\": \"jira777\" }").filter(session)
                .when().post("rest/auth/1/session")
                .then().extract().response().asString();

        String expectedMessage = "What is this";


        //Add comment
        String comment = given().pathParam("key", "APIT-2").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"body\": \""+expectedMessage+"\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").filter(session)
                .when().post("rest/api/2/issue/{key}/comment")
                .then().assertThat().statusCode(201).extract().response().asString();
        JsonPath jsc = ReusableMethods.rawToJson(comment);
        String commentId = jsc.getString("id");

        //Add attachment
        given().pathParam("key","APIT-2").header("X-Atlassian-Token", "no-check").filter(session).header("Content-Type","multipart/form-data")
                .multiPart("file", new File("C:/Git/APITesting/src/main/resources/Attachment.txt"))
                .when().post("/rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);

        //Get Issue
        String issuedetails = given().filter(session).pathParam("key", "APIT-2")
                .queryParam("fields","comment")
                .when().get("/rest/api/2/issue/{key}")
                .then().log().all().extract().response().asString();
        System.out.println(issuedetails);

        JsonPath js = ReusableMethods.rawToJson(issuedetails);
        int commentscount = js.getInt("fields.comment.comments.size()");
        for (int i=0; i<commentscount; i++){
            String commentIdIssue = js.get("fields.comment.comments["+i+"].id").toString();
            if(commentIdIssue.equalsIgnoreCase(commentId)){
                String message = js.get("fields.comment.comments["+i+"].body");
                System.out.println(message);
                Assert.assertEquals(message,expectedMessage);
            }
        }
    }
}
