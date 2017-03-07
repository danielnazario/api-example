package com.matera.store.model;


public class Product {

	private double price;
	private long id;
	private String name;
	private int quantity;
	
	public Product(long id, String name, double price, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public double getTotalPrice() {
		return quantity * price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
