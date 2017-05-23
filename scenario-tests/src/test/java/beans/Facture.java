package beans;

import java.util.List;

public class Facture {
    private String id;
    private Fournisseur fournisseur;
    private Client client;
    private List<Produit> produits;

    public Facture(String id, Fournisseur fournisseur, Client client, List<Produit> produits) {
        this.id = id;
        this.fournisseur = fournisseur;
        this.client = client;
        this.produits = produits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
}
