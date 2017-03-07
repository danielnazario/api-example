package com.matera.store;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.matera.store.model.Cart;
import com.matera.store.model.Product;
import com.thoughtworks.xstream.XStream;

import junit.framework.Assert;

public class ClientTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;

	@Before
	public void before() {
	 	this.server = Server.startServer();
	 	this.client = ClientBuilder.newClient();
	 	this.target = client.target("http://localhost:8080/");
	}

	@After
	public void stopServer() {
	   server.stop();
	}	
	
	@Test
    public void testCartGet() {
				
		String content = target.path("/carts/1").request().get(String.class);
		Cart cart = (Cart) new Gson().fromJson(content,Cart.class);
		
        Assert.assertEquals("123, Street one", cart.getStreet());
    }
	
	@Test
    public void testCartPost(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Cart cart = new Cart();
        cart.add(new Product(42L, "Product 42", 999, 1));
        cart.setStreet("Street two");
        cart.setCidade("Washington");
        String json = cart.toJson();

        Entity<String> entity = Entity.entity(json, MediaType.APPLICATION_JSON);

        Response response = target.path("/carts").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");
        String content = client.target(location).request().get(String.class);
        
        Assert.assertTrue(content.contains("Product 42"));
    }
}
