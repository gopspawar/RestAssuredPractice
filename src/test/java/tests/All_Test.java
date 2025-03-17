package tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class All_Test 
{

	@Test
	public void GetMethod() 
	{
    Allure.step("Starting Get request");
	given()
    .when()
    .get("https://reqres.in/api/users?page=2")
    .then()
    .statusCode(200)
    .body("data.size()", greaterThan(0));
}
	@Test
	public void PostMethod() {
		
//		String requestBody = """
//                {
//                    "name": "Gopal Pawar",
//                    "job": "QA Engineer"
//                }
//                """;
//		
//		 given()
//         .header("Content-Type", "application/json")
//         .body(requestBody)
//     .when()
//         .post("https://reqres.in/api/users")
//     .then()
//         .statusCode(201)
//         .body("name", equalTo("Gopal Pawar"));
		 
		 //
		String requestBody = """
	            {
	                "name": "Gopal Pawar",
	                "job": "QA Engineer"
	            }
	            """;

	    // Sending POST request
	    Response response = given()
	        .header("Content-Type", "application/json")
	        .body(requestBody)
	    .when()
	        .post("https://reqres.in/api/users");

	    // Print response for debugging
	    System.out.println("Response: " + response.getBody().asString());

	    // Assertions using RestAssured Matchers
	    response.then()
	        .statusCode(201)  // Validate HTTP Status Code
	        .body("name", equalTo("Gopal Pawar"))  // Validate 'name' field in response
	        .body("job", equalTo("QA Engineer"))  // Validate 'job' field
	        .body("id", notNullValue())  // Ensure 'id' is generated
	        .body("createdAt", notNullValue());  // Ensure 'createdAt' timestamp is present

	    // Assertions using TestNG
	    Assert.assertEquals(response.getStatusCode(), 201, "Status code mismatch!");
	    Assert.assertTrue(response.getBody().asString().contains("Gopal Pawar"), "Response does not contain expected name!");
	    Assert.assertNotNull(response.jsonPath().getString("id"), "ID is null!");
	    Assert.assertNotNull(response.jsonPath().getString("createdAt"), "CreatedAt is null!");

	    // Header Assertions
	    Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8", "Content-Type mismatch!");
		 
	}
	
	@Test
	public void putMethod() 
	{
		given()
	    .queryParam("userId", "123")
	    .header("Content-Type", "application/json")
	    .body("{ \"name\": \"Updated Name\", \"job\": \"Senior QA\" }")
	.when()
	    .put("https://reqres.in/api/users/123")
	.then()
	    .statusCode(200)
	    .body("job", equalTo("Senior QA"));
	}
	@Test
	public void deleteMethod() {
		given()
	    .when()
	    .delete("https://reqres.in/api/users/123")
	    .then()
	    .statusCode(204);  // No Content (successful delete)

	}
}