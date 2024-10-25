package hh.ma.ac.inpt.service;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import hh.ma.ac.inpt.domaine.Product;
import hh.ma.ac.inpt.domaine.ProductsWrapper;
import hh.ma.ac.inpt.exceptions.NoSuchProductException;
import jakarta.jws.WebService;

@WebService(endpointInterface = "hh.ma.ac.inpt.service.ProductManager")
public class ProductsManagerImpl implements ProductManager {

    private static long nextProductId = 0;
    private static List<Product> products = new ArrayList<Product>();

    @Override
    public long addProduct(Product product) {
       
        nextProductId++;
        product.setId(nextProductId);
        products.add(product);

        return product.getId();
    }

    @Override
    public Product getProduct(long id) throws NoSuchProductException {
        for (Product product : products) {
            if (id == product.getId()) {
                return product;
            }
        }
        throw new NoSuchProductException("No Such Product");
    }
    @Override
    public ProductsWrapper getAllProducts() {
        ProductsWrapper wrapper = new ProductsWrapper();
        wrapper.setProducts(products);
        return wrapper;
    }

	@Override
	public void DeleteProduct(long id) throws NoSuchProductException {
		 Iterator<Product> iterator = products.iterator();
	        while (iterator.hasNext()) {
	            Product product = iterator.next();
	            if (product.getId() == id) {
	                iterator.remove(); 
	                return;
	            }
	        }
	        throw new NoSuchProductException("No Such Product");		
	}

	@Override
	public Product updateProduct(long id, Product productUpdates) throws NoSuchProductException {
		  for(Product product : products) {
			  if(product.getId() == id) {
				 product.setLabel(productUpdates.getLabel());
				 product.setPrice(productUpdates.getPrice());
				 return product;
			  }
		  }
		  throw new NoSuchProductException("No Product fount with this ID"+id);
	}
	}