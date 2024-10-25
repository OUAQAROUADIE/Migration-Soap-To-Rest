package com.studentmanagment.service;

import com.studentmanagment.exception.NoSuchProductException;
import com.studentmanagment.model.Product;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductManager {

    private static long nextProductId = 0;
    private static List<Product> products = new ArrayList<>();

    @Override
    public long addProduct(Product product) {
        nextProductId++;
        product.setId(nextProductId);
        products.add(product);
        return product.getId();
    }

    @Override
    public Product getProduct(long id) throws NoSuchProductException {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchProductException("No Such Product"));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product updateProduct(long id, Product productUpdates) throws NoSuchProductException {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setLabel(productUpdates.getLabel());
                product.setPrice(productUpdates.getPrice());
                return product;
            }
        }
        throw new NoSuchProductException("Product not found with ID: " + id);
    }

    @Override
    public void DeleteProduct(long id) throws NoSuchProductException {
        Product tmpProduct = null;
        for (Product product : products) {
            if (product.getId() == id) {
                tmpProduct = product;
                break;
            }
        }
        if (tmpProduct == null) {
            throw new NoSuchProductException("Product not found with ID" + id);
        }
        products.remove(tmpProduct);
    }


}