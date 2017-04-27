package fr.sogeti.scenarioTest;

import beans.CartElement;
import beans.Categorie;
import beans.Client;
import beans.Facture;
import beans.Fournisseur;
import beans.Panier;
import beans.Produit;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static java.lang.String.format;
import java.util.Arrays;
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

	Client client1 = new Client(555, "firstnamefjifj", "last name fjie", "mail@mail.fr", "passijjfeij",
		"4 rue jean paul de pierre", "040504938");
	Produit produit1 = new Produit(343, "reference 1", "designation 1", "image.png", 15.5, 144, 123);
	Categorie categorie1 = new Categorie(3243, "description 1", "potatoes", Arrays.asList(produit1));
	Fournisseur fournisseur1 = new Fournisseur(6454, "company 1", "phone 1", "mail@gmail.com");
	CartElement cartElement = new CartElement(1, produit1.getId(), 1, produit1.getPrice());
	Panier panier1 = new Panier(213123, Arrays.asList(cartElement), fournisseur1.getId(), produit1.getPrice());
	Facture facture1 = new Facture("", fournisseur1, client1, Arrays.asList(produit1));

	return new Object[][] { new Object[] { client1, produit1, categorie1, fournisseur1, panier1, facture1 } };
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
	clean(client, produit, categorie, fournisseur, panier, facture);

    }

    private void creerClient(Client client) {

	String customerJSON = format(
		"{" + "\"id\": %d," + "\"firstname\": \"%s\"," + "\"lastname\": \"%s\"," + "\"email\": \"%s\","
			+ "\"credentials\":{" + "\"email\": \"%s\"," + "\"passWord\": \"%s\"" + "},"
			+ "\"address\": \"%s\"," + "\"phoneNumber\": \"%s\"" + "}",
		client.getId(), client.getFirstname(), client.getLastname(), client.getEmail(), client.getEmail(),
		client.getPasssword(), client.getAddress(), client.getPhoneNumber());

	given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").then()
		.body(containsString("" + client.getId()));

	System.out.println("customer created");
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

	System.out.println("client retrieved");
    }

    /**
     * Creer un fournisseur
     * 
     * @param fournisseur
     */
    private void creationFounisseur(Fournisseur fournisseur) {

	final String json = format("{" + "\"company\": \"%s\"," + "\"mail\": \"%s\"," + "\"phone\": \"%s\"" + "}",
		fournisseur.getCompany(), fournisseur.getMail(), fournisseur.getId(), fournisseur.getPhone());
	Integer id = given().contentType(ContentType.JSON).body(json).when().post(ROUTE_FOURNISSEUR).then().assertThat()
		.statusCode(201).extract().path("id");
	fournisseur.setId(id);

	System.out.println("supplier created");
    }

    /**
     * Vérifie un fournisseur, vérifie si il y a bien son id et le nom de son
     * entreprise
     * 
     * @param fournisseur
     */
    private void recupFounisseur(Fournisseur fournisseur) {

	final String route = ROUTE_FOURNISSEUR + "/" + fournisseur.getId();
	get(route).then().assertThat().body("id", equalTo(fournisseur.getId()));
	get(route).then().assertThat().body("company", equalTo(fournisseur.getCompany()));
	get(route).then().assertThat().contentType(ContentType.JSON);
	get(route).then().assertThat().statusCode(200);

	System.out.println("supplier retrieved");
    }

    /**
     * Créer une categorie
     * 
     * @param categorie
     */
    private void creationCategorie(Categorie categorie) {

	final String json = format("{" + "\"description\": \"%s\"," + "\"name\": \"%s\"" + "}",
		categorie.getDescription(), categorie.getName());
	int id = given().contentType(ContentType.JSON).body(json).when().post(ROUTE_CATEGORY).then().assertThat()
		.statusCode(201).extract().path("id");
	categorie.setId(id);

	System.out.println("category created");
    }

    private void recupCategorie(Categorie categorie) {

	final String route = ROUTE_CATEGORY + "/" + categorie.getId();
	get(route).then().assertThat().body("description", equalTo(categorie.getDescription()));
	get(route).then().assertThat().body("id", equalTo(categorie.getId()));
	get(route).then().assertThat().body("name", equalTo(categorie.getName()));
	get(route).then().assertThat().statusCode(200);
	get(route).then().assertThat().contentType(ContentType.JSON);

	System.out.println("retrieved category");
    }

    private void creationProduit(Produit produit) {

	Gson gson = new Gson();
	final String s = gson.toJson(produit);
	final JsonObject object = gson.fromJson(s, JsonObject.class);
	object.remove("id");
	String json = gson.toJson(object);

	int id = given().contentType(ContentType.JSON).body(json).when().post(ROUTE_PRODUIT).then().assertThat()
		.statusCode(201).extract().path("id");
	produit.setId(id);

	System.out.println("product created");
    }

    public void recupProduit(Produit produit) {

	final String route = ROUTE_PRODUIT + "/" + produit.getId();
	get(route).then().assertThat().body("designation", equalTo(produit.getDesignation()));
	get(route).then().assertThat().body("id", equalTo(produit.getId()));
	get(route).then().assertThat().body("idCategory", equalTo(produit.getIdCategory()));
	get(route).then().assertThat().body("idSupplier", equalTo(produit.getIdSupplier()));
	get(route).then().assertThat().body("image", equalTo(produit.getImage()));
	float price = (float) produit.getPrice();
	get(route).then().assertThat().body("price", equalTo(price));
	get(route).then().assertThat().body("reference", equalTo(produit.getReference()));
	get(route).then().assertThat().contentType(ContentType.JSON);
	get(route).then().assertThat().statusCode(200);
    }

    private void rechercheProduit(Produit produit) {

	// TODO : not implemented yet.
    }

    private void verifPanierVide(Client client) {

    }

    private void verifProduitDansCategorie(Categorie categorie, Produit produit) {

	given().when().get(ROUTE_PRODUIT + "/" + produit.getId()).then().assertThat().body("idCategory",
		equalTo(produit.getIdCategory()));

	System.out.println("product category checked");
    }

    private void achat(Client client) {

	// TODO : not implemented yet.
    }

    private void ajoutFacture(Panier panier) {
	//
	// String factureJSON = format("", args)
	//
	// given().content(ContentType.JSON).body()

	// TODO: waiting for bills to be refactored.
    }

    private void recupFacture(Facture facture) {

	// TODO: waiting for bills to be refactored.
    }

    private void ajoutProduitPanier(Produit produit, Client client) {

	String panierJSON = format(
		"{\"ID\":%d," + "\"CartElements\":[{\"ElementID\":1,\"ProductID\":%d,\"Quantity\":%d,\"UnitPrice\":%f}]"
			+ ",\"TimeStamp\":\"\",\"CustomerID\":%d,\"TotalPrice\":%d}",
		client.getId(), produit.getId(), 1, produit.getPrice(), client.getId(), 0);

	given().content(ContentType.JSON).body(panierJSON).when().post(ROUTE_PANIER).then().assertThat().body("message",
		equalTo("OK"));

	System.out.println("product added");
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
	// TODO: waiting for bills to be refactored.

    }

    private void deleteClient(Client client) {

	given().when().delete(ROUTE_CLIENT + "/" + client.getId()).then().assertThat().statusCode(200);
	System.out.println("Customer succesfully deleted");

    }

    private void deleteCategorie(Categorie categorie) {

	given().when().delete(ROUTE_CATEGORY + "/" + categorie.getId()).then().assertThat().statusCode(200);
	System.out.println("Category successfully deleted");

    }

    private void deleteFournisseur(Fournisseur fournisseur) {

	given().when().delete(ROUTE_FOURNISSEUR + "/" + fournisseur.getId()).then().assertThat().statusCode(200);
	System.out.println("Supplier successfully deleted");

    }

    private void deleteProduit(Produit produit) {

	given().when().delete(ROUTE_PRODUIT + "/" + produit.getId()).then().assertThat().statusCode(200);
	System.out.println("Product successfully deleted");

    }
}
