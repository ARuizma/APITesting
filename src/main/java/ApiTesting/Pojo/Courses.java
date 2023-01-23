package ApiTesting.Pojo;

import java.util.List;

public class Courses {

    private List<WebAutomation> webAutomation;
    private List<api> API;
    private List<Mobile> Mobile;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<api> getAPI() {
        return API;
    }

    public void setAPI(List<api> API) {
        this.API = API;
    }

    public List<Mobile> getMobile() {
        return Mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        Mobile = mobile;
    }
}
