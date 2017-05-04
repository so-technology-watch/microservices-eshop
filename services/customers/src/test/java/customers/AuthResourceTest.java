package customers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.Gson;

import domain.Credentials;
import domain.Customer;
import elements.AuthStatus;
import elements.AuthToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthResourceTest extends FonctionalTest {

    @Test
    public void Aauthentification() {

	String credentials = "{\"email\":\"mail2@mail.fr\",\"passWord\":\"passijjfeij\"}";

	given().contentType("application/json").body(credentials).post("/auth").then().assertThat().body("code", equalTo(0));

    }

    @Test
    public void BauthStatus() {

	String id = "385984b5-e7f6-4fcf-8309-27a995088927";
	AuthToken authToken = new AuthToken(id);

	Gson gson = new Gson();

	given().header("Authorization", "Bearer " + authToken.encodeToJWT()).when().get("/auth").then()
		.body(containsString(gson.toJson(new AuthStatus(AuthStatus.CODE_AUTH, AuthStatus.MSG_AUTH))));

    }

    @Test
    public void deleteToken() {

	Customer customer = new Customer("", "jean", "paul", "jean.paul@mail.com", "4 rue de la bergerie", "0956787678",
		new Credentials("jean.paul@mail.com", "pwd"));

	AuthToken authToken = new AuthToken(customer.getId());

	given().when().delete("/auth/" + authToken.encodeToJWT()).then()
		.body(containsString("Authentification token as successfully been deleted"));

    }
}
