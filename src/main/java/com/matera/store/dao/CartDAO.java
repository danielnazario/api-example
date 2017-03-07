package com.matera.store.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.matera.store.model.Cart;
import com.matera.store.model.Product;

public class CartDAO {
	
	private static Map<Long, Cart> database = new HashMap<Long, Cart>();
	private static AtomicLong cont = new AtomicLong(1);
	
	static {
		Product product1 = new Product(6237, "Product 1", 4000, 1);
		Product product2 = new Product(3467, "Product 2", 60, 2);
		Cart cart = new Cart()
								.add(product1)
								.add(product2)
								.sendTo("123, Street one", "New York")
								.setId(1l);
		database.put(1l, cart);
	}
	
	public void add(Cart cart) {
		long id = cont.incrementAndGet();
		cart.setId(id);
		database.put(id, cart);
	}
	
	public Cart search(Long id) {
		return database.get(id);
	}
	
	public Cart remove(long id) {
		return database.remove(id);
	}

}
