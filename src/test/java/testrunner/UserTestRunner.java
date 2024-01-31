package testrunner;

import controller.User;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner {
    @Test(priority = 1, description = "User should login")
    public void doLogin() throws ConfigurationException, IOException {
        User user = new User();
        user.doLogin("salman@roadtocareer.net", "1234");
    }
//    @Test(priority = 2, description = "Should get user info")
    public void getUserInfo() throws IOException {
        User user = new User();
        user.getUserInfo();
    }
}
