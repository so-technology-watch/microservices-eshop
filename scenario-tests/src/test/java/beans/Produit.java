package beans;

public class Produit {
	private int id;
    private String reference;
    private String designation;
    private String image;
    private double price;
    private int idSupplier;
    private int idCategory;

    public Produit(int id, String reference, String designation, String image, double price, int idSupplier, int idCategory) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.image = image;
        this.price = price;
        this.idSupplier = idSupplier;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    
}
