package beans;

import java.util.List;


public class Panier {
    private int id;
    private List<Integer> cartElement;
    private int idCustomer;
    private double price;

    public Panier(int id, List<Integer> cartElement, int idCustomer, double price) {
        this.id = id;
        this.cartElement = cartElement;
        this.idCustomer = idCustomer;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getCartElement() {
        return cartElement;
    }

    public void setCartElement(List<Integer> cartElement) {
        this.cartElement = cartElement;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
