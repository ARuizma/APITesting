package ApiTesting.StandardAPI;

public class Payload {
    public static String AddPlace() {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String UpdatePlace(String placeID, String newAddress) {
        return "{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    public static String CoursePrice() {
        return "{\n" +
                "\t\"dashboard\": {\n" +
                "\t\t\"purchaseAmount\": 1162,\n" +
                "\t\t\"website\": \"rahulshettyacademy.com\"\n" +
                "\t},\n" +
                "\t\"courses\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Selenium Python\",\n" +
                "\t\t\t\"price\": 50,\n" +
                "\t\t\t\"copies\": 6\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Cypress\",\n" +
                "\t\t\t\"price\": 40,\n" +
                "\t\t\t\"copies\": 4\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"RPA\",\n" +
                "\t\t\t\"price\": 45,\n" +
                "\t\t\t\"copies\": 10\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"Appium\",\n" +
                "\t\t\t\"price\": 36,\n" +
                "\t\t\t\"copies\": 7\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

    public static String Addbook(String isbn, String aisle){
        return "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foer\"\n" +
                "}";
    }
}
