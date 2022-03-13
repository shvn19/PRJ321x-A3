package model;

public class CartDetails {
	private Product product;
	private int quantity;
	private float amount;
	
	public CartDetails(Product product, int quantity, float amount) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
}
