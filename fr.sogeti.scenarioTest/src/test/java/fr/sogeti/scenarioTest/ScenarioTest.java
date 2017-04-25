package fr.sogeti.scenarioTest;

import beans.Categorie;
import beans.Client;
import beans.Facture;
import beans.Fournisseur;
import beans.Panier;
import beans.Produit;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static java.lang.String.format;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ScenarioTest extends FonctionalTest {

    private static final String ROUTE_CATEGORY = "/categories";
    private static final String ROUTE_CLIENT = "/customers";
    private static final String ROUTE_FACTURE = "/bills";
    private static final String ROUTE_FOURNISSEUR = "/suppliers";
    private static final String ROUTE_PANIER = "/carts";
    private static final String ROUTE_PRODUIT = "/products";
    
	public static Object[][] params() {

		return new Object[][] {};
	}

	@Test
	@Parameters(method = "params")
	public void run(Client client, Produit produit, Categorie categorie, Fournisseur fournisseur, Panier panier,
			Facture facture) {

		creerClient(client);
		recupClient(client);
		creationFounisseur(fournisseur);
		recupFounisseur(fournisseur);
		creationCategorie(categorie);
		recupCategorie(categorie);
		creationProduit(produit);
		recupProduit(produit);
		rechercheProduit(produit);
		verifProduitDansCategorie(categorie, produit);
		ajoutProduitPanier(produit, client);
		achat(client);
		ajoutFacture(panier);
		recupFacture(facture);
		verifPanierVide(client);

	}
    private void creerClient(Client client) {

		String customerJSON = format(
				"{\"id\":%d,\"firstname\":\"%s\",\"lastname\":\"%s\",\"email\":\"%s\","
						+ "\"credentials\":{\"email\":\"%s\",\"password\":\"%s\"},"
						+ "\"address\":\"%s\",\"phoneNumber\":\"%s\"}",
				client.getId(), client.getFirstname(), client.getLastname(), client.getEmail(), 
                client.getEmail(), client.getPasssword(), client.getAddress(), client.getPhoneNumber());
		System.out.println(customerJSON);

		given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").then()
				.body(containsString("" + client.getId()));
	}
    
    public void recupClient(Client client) {
		String route = ROUTE_CLIENT + "/" + client.getId();
		get(route).then().assertThat().body("id", equalTo(client.getId()));
		get(route).then().assertThat().body("firstname", equalTo(client.getFirstname()));
		get(route).then().assertThat().body("lastname", equalTo(client.getLastname()));
		get(route).then().assertThat().body("email", equalTo(client.getEmail()));
		get(route).then().assertThat().body("address", equalTo(client.getAddress()));
		get(route).then().assertThat().body("phoneNumber", equalTo(client.getPhoneNumber()));

		get(route).then().assertThat().contentType(ContentType.JSON);
		get(route).then().assertThat().statusCode(200);
	}
    
	private void creationFounisseur(Fournisseur fournisseur) {
	}
    
	private void recupFounisseur(Fournisseur fournisseur) {
	}
    
    
	private void creationCategorie(Categorie categorie) {
	}
    
    public void recupProduit(Produit produit) {
	}
    
    private void creationProduit(Produit produit) {
	}
    
    private void rechercheProduit(Produit produit) {
	}
    
    
	private void verifPanierVide(Client client) {
	}

    private void verifProduitDansCategorie(Categorie categorie, Produit produit) {
	}
    
    private void achat(Client client) {
	}
    
    private void ajoutFacture(Panier panier) {
	}
    
    private void recupFacture(Facture facture) {
	}
    
    private void recupCategorie(Categorie categorie) {
	}
    
	private void ajoutProduitPanier(Produit produit, Client client) {
	}

	public void clean(Client client, Produit produit, Categorie categorie, Fournisseur fournisseur, Panier panier,
			Facture facture) {
		deleteClient(client);
		deleteProduit(produit);
		deleteFournisseur(fournisseur);
		deleteCategorie(categorie);
		deleteFacture(facture);
	}

	private void deleteFacture(Facture facture) {
	}

	private void deleteClient(Client client) {
	}

	private void deleteCategorie(Categorie categorie) {
	}

	private void deleteFournisseur(Fournisseur fournisseur) {
	}

	private void deleteProduit(Produit produit) {
	}
}
