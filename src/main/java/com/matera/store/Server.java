package com.matera.store;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {

	public static HttpServer startServer() {
	    
		
		ResourceConfig config = new ResourceConfig().packages("com.matera.store");
	    URI uri = URI.create("http://localhost:8080/");
	       
	    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
	 	   
	    return server;
	    
	}
	
	public static void main(String[] args) throws IOException{
		HttpServer server = startServer();
		System.out.println("Server up");
		System.in.read();
		server.stop();
	}
	
}

