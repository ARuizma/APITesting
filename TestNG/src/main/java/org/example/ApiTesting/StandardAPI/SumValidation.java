package org.example.ApiTesting.StandardAPI;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses()
    {
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int sum = 0;
        int count = js.getInt("courses.size()");
        for(int i=0;i<count;i++) {
            int prices = js.get("courses["+i+"].price");
            int copies = js.get("courses["+i+"].copies");
            int amount = prices * copies;
            System.out.println(amount);
            sum = sum + amount;
        }
        System.out.println(sum);
        int purchaseamount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseamount);
    }
}
