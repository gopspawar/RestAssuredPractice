package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*; // Import Allure
import static io.restassured.RestAssured.given;

public class UserTests extends BaseTest {

    @Test
    @Epic("User API Tests")    // High-level feature
    @Feature("Get User")       // Specific feature being tested
    @Story("Verify GET /users/2 API response")  // Story
    @Severity(SeverityLevel.CRITICAL)  // Define test importance
    @Description("Test to fetch user details and validate response data")
    @Step("Sending GET request to /users/2 and validating response")
    public void testGetUser() {
        try {
            // Log start of test
            Allure.step("Starting Get User Test");

            // Send GET request
            Response response = given()
                    .header("Content-Type", "application/json")
                    .when()
                    .get("/users/2");

            // Log request and response in Allure
            Allure.addAttachment("Request", "GET /users/2");
            Allure.addAttachment("Response", response.getBody().asString());

            // Validate status code
            Assert.assertEquals(response.statusCode(), 200);
            Allure.step("Validated Status Code: 200");

            // Validate response body
            String userName = response.jsonPath().getString("data.first_name");
            Assert.assertEquals(userName, "Janet");
            Allure.step("Validated User Name: Janet");

        } catch (Exception e) {
            Allure.step("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test encountered an error: " + e.getMessage());
        }
    }
}
