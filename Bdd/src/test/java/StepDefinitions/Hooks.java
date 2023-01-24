package StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.messages.types.StepDefinition;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinitions m = new StepDefinitions();
        if(StepDefinitions.place_id ==null)
        {
            m.add_place_payload("aaaa","French","Zimbaue");
            m.call_request("addPlaceAPI","POST");
            m.verifyPlace_IdCreatedMapsToUsing("aaaa","getPlaceAPI");
        }
    }
}
