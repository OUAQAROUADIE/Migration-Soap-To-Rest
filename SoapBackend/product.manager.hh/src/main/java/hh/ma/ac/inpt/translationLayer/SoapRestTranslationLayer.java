package hh.ma.ac.inpt.translationLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import hh.ma.ac.inpt.domaine.Product;
import hh.ma.ac.inpt.domaine.ProductsWrapper;
import hh.ma.ac.inpt.exceptions.NoSuchProductException;
import hh.ma.ac.inpt.service.ProductManager;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import okhttp3.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

@WebService(endpointInterface = "hh.ma.ac.inpt.service.ProductManager")
public class SoapRestTranslationLayer implements ProductManager {

    private static final String REST_ENDPOINT_URL = "http://localhost:8080/products";

    //Sérialiser / désérialiser entre les chaines JSON/ Objets Java
    private final ObjectMapper objectMapper;
    
    //pour créer http requests
    private final OkHttpClient httpClient;

    public SoapRestTranslationLayer() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = new OkHttpClient();
    }

    @Override
    public long addProduct(Product product) throws NoSuchProductException {
    	try {
            // Convert the product to JSON
            String jsonProduct = objectMapper.writeValueAsString(product);

            // Make REST POST request to add the product
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonProduct);
            Request request = new Request.Builder()
                    .url(REST_ENDPOINT_URL)
                    .post(body)
                    .build();
            Response response = httpClient.newCall(request).execute();

            // Check if the product was successfully added
            if (response.isSuccessful()) {
            	
                // Parse the response to get the product ID
                String responseBody = response.body().string();
                long productId = Long.parseLong(responseBody);

                
                // Return the product ID
                return productId;
            } else {
                throw new NoSuchProductException("Failed to add product via REST");
            }
        }  catch (IOException e) {
            throw new NoSuchProductException("Failed to add product via REST: " + e.getMessage());
        }
    }

    @Override
    public Product getProduct(long id) throws NoSuchProductException {
        try {
            Request request = new Request.Builder()
                    .url(REST_ENDPOINT_URL + "/" + id)
                    .get()
                    .build();
            Response response = httpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Product product = objectMapper.readValue(responseBody, Product.class);

                System.out.println("SOAP Response: " + product);

                return product;
            } else {
                throw new NoSuchProductException("Product not found via REST");
            }
        } catch (IOException e) {
            throw new NoSuchProductException("Failed to retrieve product via REST: " + e.getMessage());
        }
    }
    @Override
    public ProductsWrapper getAllProducts() {
        try {
            Request req = new Request.Builder()
                    .url(REST_ENDPOINT_URL)
                    .get().build();
            Response res = httpClient.newCall(req).execute();
            if (res.isSuccessful()) {
                String responseBody = res.body().string();
                Product[] products = objectMapper.readValue(responseBody, Product[].class);
                ArrayList<Product> productList = new ArrayList<>(Arrays.asList(products)); // Convertir le tableau en ArrayList
                
                ProductsWrapper wrapper = new ProductsWrapper();
                wrapper.setProducts(productList);
                
                System.out.println("SOAP RESPONSE : " + productList);
                return wrapper;
            } else {
                return new ProductsWrapper();
            }
        } catch (IOException e) {
            System.err.println("Failed to retrieve Products: " + e.getMessage());
            return new ProductsWrapper();
        }
    }




	


	@Override
	public void DeleteProduct(long id) throws NoSuchProductException {
		 try {
		        Request request = new Request.Builder()
		                .url(REST_ENDPOINT_URL + "/" + id)
		                .delete()
		                .build();
		        
		        Response response = httpClient.newCall(request).execute();

		        if (!response.isSuccessful()) {
		            throw new NoSuchProductException("Failed to delete product via REST");
		        }
		    } catch (IOException e) {
		        throw new NoSuchProductException("Failed to delete product via REST: " + e.getMessage());
		    }		
	}

	@Override
	public Product updateProduct(long id, Product productUpdates) throws NoSuchProductException {
	    try {
	        String jsonProductUpdates = objectMapper.writeValueAsString(productUpdates);

	        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonProductUpdates);
	        Request request = new Request.Builder()
	                .url(REST_ENDPOINT_URL + "/" + id)
	                .put(body)
	                .build();

	        Response response = httpClient.newCall(request).execute();

	        if (response.isSuccessful()) {
	            // Imprimer un message indiquant que le produit a été modifié avec succès
	            System.out.println("Produit modifié avec succès !");
	            
	            // Récupérer la réponse SOAP et l'afficher
	            String soapResponse = response.body().string();
	            System.out.println("SOAP Response: " + soapResponse);
	            
	            return productUpdates;
	        } else {
	            throw new NoSuchProductException("Failed to update product via REST");
	        }
	    } catch (IOException e) {
	        throw new NoSuchProductException("Failed to update product via REST: " + e.getMessage());
	    }
	}

}