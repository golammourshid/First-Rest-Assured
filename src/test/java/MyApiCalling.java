import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class MyApiCalling {
    Properties prop;
    @Test
    public void doLogin() throws ConfigurationException, IOException {
        readConfig();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "\t\"email\":\"salman@roadtocareer.net\",\n" +
                                "\t\"password\":\"1234\"\n" +
                                "}")
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();
//        System.out.println(res.asPrettyString());

        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        System.out.println(token);
        saveEnvVar("token", token);
    }
    @Test
    public void getUserInfo() throws IOException {
        readConfig();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").header("Authorization", prop.getProperty("token"))
                .when().get("/user/search/id/15800")
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(res.asPrettyString());
        JsonPath jsonObj = res.jsonPath();
        String name = jsonObj.get("name");
        System.out.println(name);
    }
    public void saveEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }

    public void readConfig() throws IOException {
        prop = new Properties();
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        prop.load(file);
    }
}
