package resources;

import org.example.ApiTesting.Pojo.AddPlace;
import org.example.ApiTesting.Pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address){
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("2222222");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);

        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("park");
        p.setTypes(myList);

        Location l = new Location();
        l.setLat(-3.87454);
        l.setlng(35.4778);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String place_id){

        return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
    }
}
