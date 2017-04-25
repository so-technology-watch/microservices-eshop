package fr.sogeti.scenarioTest;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ScenarioTest extends FonctionalTest {

    public static Object[][] params(){
        return new Object[][]{
            new Object[]{2, "jean", "paul", "jean.paul2@mail.com", "password", "4 rue de la bergerie", "0956787678"},
            new Object[]{3, "paul", "jean", "guillaume@mail.com", "fjeufhufrdk", "5 rue de la pierre ronde", "6060606065"},
            new Object[]{2, "guss", "paulin", "jean.paul2@mail.com", "password", "6 rue jean paul", "08979594328"},
            new Object[]{2, "guss", "paulin", "jean.paul2@mail.com", "password", "6 rue jean paul", "08979594328"},
            new Object[]{2, "same", "mail", "jean.paul2@mail.com", "password", "6 rue jean paul", "08979594328"},
        };
    }
    
	@Test
    @Parameters(method = "params")
	public void run(int id, String firstname, String lastname, String email, String password, String address, String phoneNumber) {

        creerClient(id, firstname, lastname, email, password, address, phoneNumber);
        recupClient(id, firstname, lastname, email, address, phoneNumber);
        deleteClient(id);
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
    
    public void recupClient(int id, String firstname, String lastname, String email, String address, String phoneNumber) {
        String route = "/customers/" + id;
        get(route).then().assertThat().body("id", equalTo(id));
        get(route).then().assertThat().body("firstname", equalTo(firstname));
        get(route).then().assertThat().body("lastname", equalTo(lastname));
        get(route).then().assertThat().body("email", equalTo(email));
        get(route).then().assertThat().body("address", equalTo(address));
        get(route).then().assertThat().body("phoneNumber", equalTo(phoneNumber));
        
        get(route).then().assertThat().contentType(ContentType.JSON);
        get(route).then().assertThat().statusCode(200);
    }

    public void creerProduit() {
    }
    
    public void recupProduit() {
        
    }
    
    public void rechercheProduit() {
        
    }
    
    public void clientAjouteProduitPanier() {
        
    }
    
    public void clientConsultePanier(){
        
    }
    
    public void clientAchete(){
        
    }
    
    public void consulterFacture(){
        
    }
    

    public void deleteClient(int id) {
        delete("/customers/" + id).then().assertThat().statusCode(200);
    }
}
