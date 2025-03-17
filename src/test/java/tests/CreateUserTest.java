package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("API Testing Demo")
@Feature("User Management")
public class CreateUserTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserTest.class);

    @Test(description = "Create a new user and validate the response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create User")
    @Description("This test sends a POST request to create a user and validates the response")
    public void testCreateUser() {
        String requestBody = """
                {
                    "name": "Gopal Pawar",
                    "job": "QA Engineer"
                }
                """;

        Allure.step("Sending POST request with payload");
        logger.info("Sending POST request: " + requestBody);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users");

        Allure.step("Validating the response");
        logger.info("Response received: " + response.getBody().asString());

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "Gopal Pawar");

        Allure.step("User created successfully!");
        logger.info("Test Passed: User created successfully");
    }
}
