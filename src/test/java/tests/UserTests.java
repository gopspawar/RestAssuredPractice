package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import utils.ExtentManager;
import com.aventstack.extentreports.ExtentTest;

import static io.restassured.RestAssured.given;

public class UserTests extends BaseTest {

    @Test
    public void testGetUser() {
        ExtentTest test = ExtentManager.createInstance().createTest("Get User Test");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/users/2");

        test.log(Status.INFO, "Sending GET request to /users/2");
        test.log(Status.INFO, "Response received: " + response.getBody().asString());

        Assert.assertEquals(response.statusCode(), 200);

        test.log(Status.PASS, "Test passed successfully");
        ExtentManager.createInstance().flush();
    }
}
