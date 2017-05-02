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
    private static final String ROUTE_ACHAT = "/pay";

    public static Object[][] params() {

	Client client1 = new Client("", "firstnamefjifj", "last name fjie", "mail2@mail.fr", "passijjfeij",
		"4 rue jean paul de pierre", "040504938");
	Fournisseur fournisseur1 = new Fournisseur(6454, "company 1", "phone 1", "mail@gmail.com");
	Produit produit1 = new Produit(343, "reference 1", "designation 1", "image.png", 15.50, 444, 123);
	Categorie categorie1 = new Categorie(3243, "description 1", "potatoes", Arrays.asList(produit1));
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
	creationProduit(produit, fournisseur, categorie);
	recupProduit(produit);
	rechercheProduit(produit);
	verifProduitDansCategorie(categorie, produit);
	ajoutProduitPanier(produit, client);
	recupPanier(client);
	achat(client, facture);
	// recupFacture(facture);
	// verifPanierVide(client);
	// clean(client, produit, categorie, fournisseur, panier, facture);

    }

    private void recupPanier(Client client) {

	given().when().get(ROUTE_PANIER + "/" + client.getId()).then().assertThat().statusCode(200);
	System.out.println("cart retrieved");
    }

    private void creerClient(Client client) {

	String customerJSON = format(
		"{\"firstname\": \"%s\"," + "\"lastname\": \"%s\"," + "\"email\": \"%s\"," + "\"credentials\":{"
			+ "\"email\": \"%s\"," + "\"passWord\": \"%s\"" + "}," + "\"address\": \"%s\","
			+ "\"phoneNumber\": \"%s\"" + "}",
		client.getFirstname(), client.getLastname(), client.getEmail(), client.getEmail(),
		client.getPasssword(), client.getAddress(), client.getPhoneNumber());

	String body = given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").body()
		.asString();
	System.out.println(body);
	String id = given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").then().extract()
		.path("id");
	System.out.println(id);
	client.setId(id);
	given().contentType(ContentType.JSON).body(customerJSON).when().post("/customers").then().statusCode(200);
	System.out.println("customer created");
    }

    public void recupClient(Client client) {

	System.out.println(client.getId());
	String route = ROUTE_CLIENT + "/" + client.getId();
	System.out.println("zgz" + get(route).body().asString());
//	get(route).then().assertThat().body("firstname", equalTo(client.getFirstname()));
//	get(route).then().assertThat().body("lastname", equalTo(client.getLastname()));
//	get(route).then().assertThat().body("email", equalTo(client.getEmail()));
//	get(route).then().assertThat().body("address", equalTo(client.getAddress()));
//	get(route).then().assertThat().body("phoneNumber", equalTo(client.getPhoneNumber()));

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
	System.out.println(id);
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

    private void creationProduit(Produit produit, Fournisseur fournisseur, Categorie categorie) {

	produit.setIdSupplier(fournisseur.getId());
	produit.setIdCategory(categorie.getId());
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
	System.out.println(get(route).body().asString());

	System.out.println("product retrieved");
    }

    private void rechercheProduit(Produit produit) {

	// TODO : not implemented yet.
    }

    private void verifPanierVide(Client client) {

	// TODO
    }

    private void verifProduitDansCategorie(Categorie categorie, Produit produit) {

	given().when().get(ROUTE_PRODUIT + "/" + produit.getId()).then().assertThat().body("idCategory",
		equalTo(produit.getIdCategory()));

	System.out.println("product category checked");
    }

    private void achat(Client client, Facture facture) {

	given().when().post(ROUTE_ACHAT + "/" + client.getId()).then().assertThat().statusCode(200);
	System.out.println(client.getId());
	String bodyJSON = given().when().post(ROUTE_ACHAT + "/" + client.getId()).body().asString();
	System.out.println(bodyJSON);

    }

    private void recupFacture(Facture facture) {

	given().when().get(ROUTE_FACTURE + "/" + facture.getId()).then().assertThat().statusCode(200);

    }

    private void ajoutProduitPanier(Produit produit, Client client) {

	String total = format("%f", 0.00).replace(",", ".");
	String unitPrice = format("%f", produit.getPrice()).replace(",", ".");
	String panierJSON = format(
		"{\"id\": \"%s\"," + "\"cartElements\":[{\"elementID\":1,\"productID\":%d,\"quantity\":%d,\"unitPrice\":%s}]"
			+ ",\"timeStamp\":\"\",\"customerID\":\"%s\",\"totalPrice\":%s}",
		client.getId(), produit.getId(), 1, unitPrice, client.getId(), total);
	System.out.println(panierJSON);

	given().content(ContentType.JSON).body(panierJSON).when().post(ROUTE_PANIER).then().assertThat()
		.statusCode(201);
	System.out.println("product added to cart");
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

	given().when().delete(ROUTE_FACTURE + "/" + facture.getId()).then().assertThat().statusCode(200);

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
