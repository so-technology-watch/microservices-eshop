package customers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

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

	Customer customer = new Customer("", "jean", "paul", "jean.paul@mail.com", "4 rue de la bergerie", "0956787678",
		new Credentials("jean.paul@mail.com", "pwd"));

	Gson gson = new Gson();

	given().contentType("application/json").body(gson.toJson(customer.getCredentials())).post("/auth").then()
		.body(containsString(customer.getId() + ""));

    }

    @Test
    public void BauthStatus() {

	Customer customer = new Customer("", "jean", "paul", "jean.paul@mail.com", "4 rue de la bergerie", "0956787678",
		new Credentials("jean.paul@mail.com", "pwd"));

	AuthToken authToken = new AuthToken(customer.getId());

	Gson gson = new Gson();

	given().when().get("/auth/" + authToken.encodeToJWT()).then()
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
