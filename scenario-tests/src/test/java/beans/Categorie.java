package beans;

import java.util.List;

public class Categorie {
    private int id;
    private String description;
    private String name;
    private List<Produit> produits;

    public Categorie(int id, String description, String name, List<Produit> produits) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.produits = produits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

}
