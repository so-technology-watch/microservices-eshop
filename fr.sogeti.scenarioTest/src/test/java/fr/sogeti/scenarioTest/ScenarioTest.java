package fr.sogeti.scenarioTest;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;

import beans.Categorie;
import beans.Client;
import beans.Facture;
import beans.Fournisseur;
import beans.Panier;
import beans.Produit;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static org.hamcrest.Matchers.*;

import java.awt.Panel;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ScenarioTest extends FonctionalTest {

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

	private void verifPanierVide(Client client) {
		// TODO Auto-generated method stub

	}

	private void recupFacture(Facture facture) {
		// TODO Auto-generated method stub

	}

	private void ajoutFacture(Panier panier) {
		// TODO Auto-generated method stub

	}

	private void achat(Client client) {
		// TODO Auto-generated method stub

	}

	private void ajoutProduitPanier(Produit produit, Client client) {
		// TODO Auto-generated method stub

	}

	private void verifProduitDansCategorie(Categorie categorie, Produit produit) {
		// TODO Auto-generated method stub

	}

	private void rechercheProduit(Produit produit) {
		// TODO Auto-generated method stub

	}

	private void creationProduit(Produit produit) {
		// TODO Auto-generated method stub

	}

	private void recupProduit(Produit produit) {
		// TODO Auto-generated method stub

	}

	private void recupCategorie(Categorie categorie) {
		// TODO Auto-generated method stub

	}

	private void creationCategorie(Categorie categorie) {
		// TODO Auto-generated method stub

	}

	private void recupFounisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub

	}

	private void creationFounisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub

	}

	private void creerClient(Client client) {

		String customerJSON = String.format(
				"{\"id\":%d,\"firstname\":\"%s\",\"lastname\":\"%s\",\"email\":\"%s\","
						+ "\"credentials\":{\"email\":\"%s\",\"passWord\":\"%s\"},"
						+ "\"address\":\"%s\",\"phoneNumber\":\"%s\"}",
				id, firstname, lastname, email, email, passsword, address, phoneNumber);
		System.out.println(customerJSON);

		given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").then()
				.body(containsString("" + id));
	}

	public void recupClient(Client client) {
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

	public void recupProduit(int id, String reference, String designation, float price, int idSupplier,
			String companyName, String email, String phoneNumber, String image, int idCategory) {
		String customerJSON = String.format(
				"{\"id\":%d,\"reference\":\"%s\",\"designation\":\"%s\",\"price\":%f,"
						+ "\"supplier\":{\"id\": %d,\"companyName\":\"%s\", \"email\": \"%s\", \"phoneNumber\": \"%s\"},"
						+ "\"idSupplier\": %d,\"image\":\"%s\", \"idCategory\": %d}",
				id, reference, designation, price, idSupplier, companyName, email, phoneNumber, idSupplier, image,
				idCategory);

		given().when().get("/products/" + id).then().body(containsString(customerJSON));
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
		// TODO Auto-generated method stub

	}

	private void deleteClient(Client client) {
		// TODO Auto-generated method stub

	}

	private void deleteCategorie(Categorie categorie) {
		// TODO Auto-generated method stub

	}

	private void deleteFournisseur(Fournisseur fournisseur) {
		// TODO Auto-generated method stub

	}

	private void deleteProduit(Produit produit) {
		// TODO Auto-generated method stub

	}
}
