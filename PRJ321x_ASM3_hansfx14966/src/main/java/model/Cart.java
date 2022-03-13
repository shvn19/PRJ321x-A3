package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartDetails> items; // list of item in cart

	public Cart() {
		items = new ArrayList<>();
	}

	//add a new product to cart
	public void add(Product ci, int quantity) {
		boolean contained = false;

		// if the product's existed in cart then quantity is added
		// else add the product in the list of cartdetails
		for (CartDetails x : items) {
			if (x.getProduct().getId() == ci.getId()) {
				contained = true;
				x.setQuantity(x.getQuantity() + quantity);
				x.setAmount(x.getQuantity() * x.getProduct().getPrice());
				break;
			}
		}

		if (contained == false) {
			float amount = ci.getPrice() * quantity;
			items.add(new CartDetails(ci, quantity, amount));
		}
	}

	// save changes to cart
	public void saveChange(int id, int quantity) {
		for (CartDetails x : items) {
			if (x.getProduct().getId() == id) {
				x.setQuantity(quantity);
				x.setAmount(quantity * x.getProduct().getPrice());
				return;
			}
		}
	}

	// remove a product from cart
	public void remove(int id) {
		for (CartDetails x : items) {
			if (x.getProduct().getId() == id) {
				items.remove(x);
				return;
			}
		}
	}

	// return total amount of cart
	public double getAmount() {
		double s = 0;
		for (CartDetails x : items) {
			s += x.getAmount();
		}
		return Math.round(s * 100.0) / 100.0;
	}

	public List<CartDetails> getItems() {
		return items;
	}

}
