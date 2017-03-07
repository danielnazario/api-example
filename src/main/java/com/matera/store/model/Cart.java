package com.matera.store.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Cart {

	private List<Product> products = new ArrayList<Product>();
	private String street;
	private String city;
	private long id;

	public Cart add(Product produto) {
		products.add(produto);
		return this;
	}

	public Cart sendTo(String street, String city) {
		this.street = street;
		this.city = city;
		return this;
	}

	public Cart setId(long id) {
		this.id = id;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	public void setCidade(String city) {
		this.city = city;
	}
	
	public long getId() {
		return id;
	}
	
	public void remove(long id) {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if(product.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public void troca(Product product) {
		remove(product.getId());
		add(product);
	}

	public void changeQuantity(Product product) {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product p = (Product) iterator.next();
			if(p.getId() == product.getId()) {
				p.setQuantity(product.getQuantity());
				return;
			}
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
