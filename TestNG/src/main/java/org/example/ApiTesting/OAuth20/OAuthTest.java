package org.example.ApiTesting.OAuth20;

import ApiTesting.Pojo.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest {
    public static void main(String[] args){
        RestAssured.useRelaxedHTTPSValidation();
String[] courseTitles = {"Selenium", "Cypress", "Protractor"};
/*        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&service=lso&o2v=2&flowName=GeneralOAuthFlow");
        driver.findElement(By.id("identifierId")).sendKeys("srinath19830" + Keys.ENTER);
        driver.findElement(By.xpath("//input[contains(@type, 'password')]")).sendKeys("password"+ Keys.ENTER);
        String url = driver.getCurrentUrl();
        String partialurl = url.split("code=")[1];
        String code = partialurl.split("&scope")[0];
        System.out.println(code);*/

        String code = "https://rahulshettyacademy.com/getCourse.php?access_token=ya29.a0AfH6SMD-EytFH41srRFqd55ewFQGIJDcuEq-uhPn4UYeXmyRkY5NY0VBPYZgAk2zou8KRYZpspkrC3QtrhKS5McUsWoxCFpWoYEVFq3rN00eJCicj-_j9xv8Q-9gTumTGy5rJxgfkMrTVZSbtWnB2LSR0C1L";

        String accesstokenresponse = given().urlEncodingEnabled(false)
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accesstokenresponse);
        String accesstoken = js.getString("access_token");

        GetCourse gc=given().queryParam("access token",accesstoken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getCourses().getAPI().get(1).getCourseTitle());

        List<api> apicourses = gc.getCourses().getAPI();
        for(int i=0;i<apicourses.size();i++){
            if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apicourses.get(i).getPrice());
            }
        }

        ArrayList<String> a = new ArrayList<String>();
        List<WebAutomation> wacourses = gc.getCourses().getWebAutomation();
        for(int j=0;j<wacourses.size();j++){
                a.add(wacourses.get(j).getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedList));

       // System.out.println(gc);
    }
}
