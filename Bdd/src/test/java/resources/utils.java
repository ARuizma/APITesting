package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class utils {

    public static RequestSpecification req;

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fil = new FileInputStream("C:\\Git\\APITesting\\Bdd\\src\\test\\java\\resources\\global.properties");
        prop.load(fil);
        return prop.getProperty(key);
    }

    public RequestSpecification requestSpecification() throws IOException {

        if (req==null) {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build().relaxedHTTPSValidation();
            return req;
        }
        return req;
    }

    public String getJsonPath(Response response, String key){
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
