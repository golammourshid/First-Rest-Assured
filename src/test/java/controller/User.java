package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Test;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utils.Utils.saveEnvVar;
//import static utils.Utils.saveEnvVar;

public class User extends Setup {
    public User() throws IOException {
        initConfig();
    }
    public void doLogin(String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
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
    public void getUserInfo() throws IOException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").header("Authorization", prop.getProperty("token"))
                .when().get("/user/search/id/15800")
                .then().assertThat().statusCode(200).extract().response();
        System.out.println(res.asPrettyString());
        JsonPath jsonObj = res.jsonPath();
        String name = jsonObj.get("name");
        System.out.println(name);
    }
    public void createUser(String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
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
}
