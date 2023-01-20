package org.example;

import io.restassured.path.json.JsonPath;

import java.sql.SQLOutput;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());

        //Print No of courses
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print purchase amount
        int amount = js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);

        //Print title of first course
        String first = js.getString("courses[0].title");
        System.out.println(first);

        //Print all course titles and prices
        System.out.println("Print no of copies sold by RPA course");
        for (int i=0; i<count;i++) {
            String courseTitles = js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA")) {
                int copies = js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }
            //int coursePrices = js.get("courses["+i+"].price");
            //System.out.println(courseTitles + " costs " + coursePrices + "€");
        }

    }
}
