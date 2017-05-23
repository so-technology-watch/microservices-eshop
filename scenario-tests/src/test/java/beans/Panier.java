package beans;

import java.util.List;


public class Panier {
    private int id;
    private List<CartElement> cartElement;
    private float timeStamp;
    private int customerID;
    private double price;

    public Panier(int id, List<CartElement> cartElement, int idCustomer, double price) {
        this.id = id;
        this.cartElement = cartElement;
        this.customerID = idCustomer;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartElement> getCartElement() {
        return cartElement;
    }

    public void setCartElement(List<CartElement> cartElement) {
        this.cartElement = cartElement;
    }

    public int getIdCustomer() {
        return customerID;
    }

    public void setIdCustomer(int idCustomer) {
        this.customerID = idCustomer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public float getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(float timeStamp) {
		this.timeStamp = timeStamp;
	}
    
}
