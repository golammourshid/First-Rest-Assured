package testrunner;

import com.github.javafaker.Faker;
import controller.User;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    public UserTestRunner() throws IOException {
        initConfig();
    }

    @Test(priority = 1, description = "User should login")
    public void doLogin() throws ConfigurationException, IOException {
        User user = new User();
        user.doLogin("salman@roadtocareer.net", "1234");
    }
    @Test(priority = 2, description = "Created New User")
    public void createNewUser() throws IOException, ConfigurationException {
        User user = new User();
        int randomId = Utils.generateRandomId(1000, 9999);
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = "test" + randomId + "@test.com";
        String phone_number = "01521" + randomId + "00";
        JsonPath jsonObj = user.createUser(name, email, "123456", phone_number, "1234567", "admin");
        String msg = jsonObj.get("message");
        int id = jsonObj.get("user.id");
        System.out.println(msg);
        System.out.println("New user id is: " + id);
        Utils.saveEnvVar("userID", String.valueOf(id));
        Assert.assertTrue(msg.contains("User created"));
    }
    @Test(priority = 3, description = "Should get user info")
    public void getUserInfo() throws IOException {
        User user = new User();
        user.getUserInfo(prop.getProperty("userID"));
    }
}
