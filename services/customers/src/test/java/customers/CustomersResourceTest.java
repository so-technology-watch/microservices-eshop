package customers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Test;

import com.google.gson.Gson;

import domain.Credentials;
import domain.Customer;

public class CustomersResourceTest extends FonctionalTest {

//    @Test
//    public void getCustomerTest() {
//
//	given().when().get("/customers/1").then().body(containsString(
//		"{\"id\":1,\"firstname\":\"jean\",\"lastname\":\"paul\",\"email\":\"jean.paul@mail.com\",\"credentials\":{\"email\":\"jean.paul@mail.com\",\"passWord\":\"pwd\"},\"address\":\"4 rue de la bergerie\",\"phoneNumber\":\"0956787678\"}"));
//
//    }

    @Test
    public void getCustomersTest() {

	given().when().get("/customers/").then().statusCode(200);
    }

    @Test
    public void postCustomerTest() {

	String customer = "{\"id\":\"d0dc30dc-61bb-437c-bc45-a27f242659fa\",\"firstname\":\"firstnamefjifj\",\"lastname\":\"last name fjie\",\"email\":\"mail2@mail.fr\","
		+ "\"credentials\":{\"email\":\"mail2@mail.fr\",\"passWord\":\"passijjfeij\"},"
		+ "\"address\":\"4 rue jean paul de pierre\",\"phoneNumber\":\"040504938\"}";

	given().contentType("application/json").body(customer).when().post("/customers").then().assertThat().statusCode(200);

    }

//    @Test
//    public void putCustomerTest() {
//
//	Customer customer = new Customer("", "jean", "paul", "jean.paul3@mail.com", "4 rue du loup", "0956787678",
//		new Credentials("jean.paul3@mail.com", "pwd"));
//
//	given().contentType("application/json").body(customer).when().put("/customers").then()
//		.body(containsString(customer.getId() + ""));
//
//    }
//
//    @Test
//    public void deleteCustomerTest() {
//
//	given().when().delete("/customers/").then().body(containsString("Client has been successfully deleted"));
//    }

}