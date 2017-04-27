package beans;

public class CartElement {

	private int elementID;
	private int productID;
	private int quantity;
	private double unitPrice;

	public CartElement(int elementID, int productID, int quantity, double unitPrice) {
		this.elementID = elementID;
		this.productID = productID;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public int getElementID() {
		return elementID;
	}

	public void setElementID(int elementID) {
		this.elementID = elementID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
