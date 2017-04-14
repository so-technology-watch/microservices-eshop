package customers;

import org.junit.Test;

import domain.Credentials;
import domain.Customer;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class CustomersResourceTest extends FonctionalTest {

    @Test
    public void getCustomerTest() {

	given().when().get("/customers/1").then().body(containsString(
		"{\"id\":1,\"firstname\":\"jean\",\"lastname\":\"paul\",\"email\":\"jean.paul@mail.com\",\"credentials\":{\"email\":\"jean.paul@mail.com\",\"passWord\":\"pwd\"},\"address\":\"4 rue de la bergerie\",\"phoneNumber\":\"0956787678\"}"));

    }

    @Test
    public void getCustomersTest() {

	given().when().get("/customers/").then().statusCode(200);
    }

    @Test
    public void postCustomerTest() {

	Customer customer = new Customer(3, "jean", "paul", "jean.paul3@mail.com", "4 rue de la bergerie", "0956787678",
		new Credentials("jean.paul3@mail.com", "pwd"));

	given().contentType("application/json").body(customer).when().post("/customers").then()
		.body(containsString(customer.getId() + ""));

    }

    @Test
    public void putCustomerTest() {

	Customer customer = new Customer(3, "jean", "paul", "jean.paul3@mail.com", "4 rue du loup", "0956787678",
		new Credentials("jean.paul3@mail.com", "pwd"));

	given().contentType("application/json").body(customer).when().put("/customers").then()
		.body(containsString(customer.getId() + ""));

    }

    @Test
    public void deleteCustomerTest() {

	given().when().delete("/customers/3").then().body(containsString("Client supprimé avec succès."));
    }

}