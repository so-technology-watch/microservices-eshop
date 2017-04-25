package fr.sogeti.scenarioTest;

import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;

public class ScenarioTest extends FonctionalTest {

	@Test
	public void creerClient() {

		creerClient(2, "jean", "paul", "jean.paul2@mail.com", "pwd", "4 rue de la bergerie", "0956787678");

	}

	private void creerClient(int id, String firstname, String lastname, String email, String passsword, String address,
			String phoneNumber) {

		String customerJSON = String.format(
				"{\"id\":%d,\"firstname\":\"%s\",\"lastname\":\"%s\",\"email\":\"%s\","
						+ "\"credentials\":{\"email\":\"%s\",\"passWord\":\"%s\"},"
						+ "\"address\":\"%s\",\"phoneNumber\":\"%s\"}",
				id, firstname, lastname, email, email, passsword, address, phoneNumber);
		System.out.println(customerJSON);

		given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").then()
				.body(containsString("" + id));
	}
}
