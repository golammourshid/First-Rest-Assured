package testrunner;

import controller.User;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner {
    @Test(priority = 1, description = "User should login")
    public void doLogin() throws ConfigurationException, IOException {
        User user = new User();
        user.doLogin("salman@roadtocareer.net", "1234");
    }
    @Test(priority = 2, description = "Created New User")
    public void createNewUser() throws IOException, ConfigurationException {
        User user = new User();
        JsonPath jsonObj = user.createUser("Antor", "antor@gmail.com", "123456", "01521413600", "1234567", "admin");
        String msg = jsonObj.get("message");
        System.out.println(msg);
    }
    @Test(priority = 3, description = "Should get user info")
    public void getUserInfo() throws IOException {
        User user = new User();
        user.getUserInfo("45711");
    }
}
