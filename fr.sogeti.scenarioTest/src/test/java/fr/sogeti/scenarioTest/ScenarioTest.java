package fr.sogeti.scenarioTest;

import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Test;

public class ScenarioTest {

	@Test
	public void creerClient() {
		
		String client = "{\"CustomerID\": 1, [{\"ElementID\":2,\"ProductID\":1,\"Quantity\":1,\"UnitPrice\":1}]}";
	}

}
