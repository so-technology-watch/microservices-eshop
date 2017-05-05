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

	String credentials = "{\"email\":\"mail@mail.com\",\"password\":\"pass\"}";

	given().contentType("application/json").body(credentials).post("/auth").then().assertThat().body("code", equalTo(0));

    }

    @Test
    public void BauthStatus() {

	String id = "058f4838-ce60-44f2-8a4d-b836b81d074a";
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
