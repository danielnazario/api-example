package com.matera.store.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.matera.store.dao.CartDAO;
import com.matera.store.model.Cart;

@Path("carts")
public class CartResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String search(@PathParam("id") long id) {
		Cart cart = new CartDAO().search(id);
		return cart.toJson();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(String content){
		Cart cart = (Cart) new Gson().fromJson(content,Cart.class);
		new CartDAO().add(cart);
		URI uri = URI.create("/carts/" + cart.getId());
			
		return Response.created(uri).build();
	}
	
	@Path("{id}/produtos/{productId}")
    @DELETE
    public Response removeProduct(@PathParam("id") long id, @PathParam("productId") long productId) {
		Cart cart = new CartDAO().search(id);
        cart.remove(productId);
        return Response.ok().build();
    }
	
}