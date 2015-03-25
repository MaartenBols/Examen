package edu.ap.jaxrs;

import java.io.*;
import java.util.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;


@RequestScoped
@Path("/products")
public class ProductResource {
	
	
	
	@GET
	@Produces({"application/json"})
	public String getProductsJSON() {
		String jsonString = "{\"products\" : [";
		try {
			
			JAXBContext jaxbContext1 = JAXBContext.newInstance(ProductsJSON.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
			StreamSource json = new StreamSource(new File("D:/School/3TI/Webtech 3/Product.json"));
			
			ProductsJSON productsJSON = (ProductsJSON)jaxbUnmarshaller.unmarshal(json);
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
	

	@GET
	@Path("/{shortname}")
	@Produces({"application/json"})
	public String getProductJSON(@PathParam("shortname") String shortname) {
		String jsonString = "";
		try {
			// get all products
			JAXBContext jaxbContext1 = JAXBContext.newInstance(ProductsJSON.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
			File XMLfile = new File("D:/School/3TI/Webtech 3/REST/Products.xml");
			ProductsJSON productsXML = (ProductsJSON)jaxbUnmarshaller.unmarshal(XMLfile);
			ArrayList<Product> listOfProducts = productsXML.getProducts();
			
			// look for the product, using the shortname
			for(Product product : listOfProducts) {
				if(shortname.equalsIgnoreCase(product.getName())) {
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
	
	