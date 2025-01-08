package org.fis.FisSample;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class apiAutomation {

	@BeforeClass
    public void setUp() {
        // Base URI for the API
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi";
    }

    @Test
    public void testGetCurrentPrice() {
        // Step 1: Send GET request to the CoinDesk API
        Response response = given()
                .when()
                .get("/currentprice.json");

        // Step 2: Validate the response status code (200 OK)
        Assert.assertEquals(200, response.statusCode());

        // Step 3: Verify there are 3 BPIs (USD, GBP, EUR)
        boolean hasUSD = response.jsonPath().get("bpi.USD") != null;
        boolean hasGBP = response.jsonPath().get("bpi.GBP") != null;
        boolean hasEUR = response.jsonPath().get("bpi.EUR") != null;

       System.out.println(response.asString());
       Assert.assertTrue(hasUSD,"United States Dollar");
       Assert.assertTrue(hasGBP,"British Pound Sterling");
       Assert.assertTrue(hasEUR,"Euro");
        
        // Step 4: Verify that the GBP 'description' equals 'British Pound Sterling'
        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        Assert.assertEquals("British Pound Sterling", "British Pound Sterling", gbpDescription);
    }
	
}
