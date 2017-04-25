package beans;

import java.util.List;

public class Categorie {
    private int id;
    private String name;
    private List<Produit> produits;

    public Categorie(int id, String name, List<Produit> produits) {
        this.id = id;
        this.name = name;
        this.produits = produits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
