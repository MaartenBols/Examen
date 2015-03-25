package edu.ap.jaxrs;

import java.io.*;
import java.util.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;


@RequestScoped
@Path("/products")
public class ProductResource {
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductsJSON() {
		String jsonString = "{\"products\" : [";
		try {
			
			JAXBContext jaxbContext1 = JAXBContext.newInstance(ProductsJSON.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
			
			StreamSource json = new StreamSource(new File("D:/School/3TI/Webtech 3/Product.json"));
			
			ProductsJSON productsJSON = jaxbUnmarshaller.unmarshal(json,ProductsJSON.class).getValue();
			ArrayList<Product> listOfProducts = productsJSON.getProducts();
			
			for(Product product : listOfProducts) {
				jsonString += "{\"name\" : \"" + product.getName() + "\",";
				jsonString += "\"id\" : " + product.getId() + ",";
				
				jsonString += "\"brand\" : \"" + product.getBrand() + "\",";
				jsonString += "\"description\" : \"" + product.getDescription() + "\",";
				jsonString += "\"price\" : " + product.getPrice() + "},";
			}
			jsonString = jsonString.substring(0, jsonString.length()-1);
			jsonString += "]}";
		} 
		catch (JAXBException e) {
		   e.printStackTrace();
		}
		return jsonString;
	}
	
	//Voor 1 product

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProductJSON(@PathParam("name") String name) {
		String jsonString = "";
		try {
			// get all products
			JAXBContext jaxbContext1 = JAXBContext.newInstance(ProductsJSON.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
			
			StreamSource json = new StreamSource(new File("D:/School/3TI/Webtech 3/Product.json"));
			
			ProductsJSON productsJSON = jaxbUnmarshaller.unmarshal(json,ProductsJSON.class).getValue();
			ArrayList<Product> listOfProducts = productsJSON.getProducts();
			
			// look for the product, using the shortname
			for(Product product : listOfProducts) {
				if(name.equalsIgnoreCase(product.getName())) {
					jsonString += "{\"name\" : \"" + product.getName() + "\",";
					jsonString += "\"id\" : " + product.getId() + ",";
					
					jsonString += "\"brand\" : \"" + product.getBrand() + "\",";
					jsonString += "\"description\" : \"" + product.getDescription() + "\",";
					jsonString += "\"price\" : " + product.getPrice() + "}";
				}
			}
		} 
		catch (JAXBException e) {
		   e.printStackTrace();
		}
		return jsonString;
	}
}
	
	