package com.studentmanagment.service;

import com.studentmanagment.exception.NoSuchProductException;
import com.studentmanagment.model.Product;

import java.util.List;

public interface ProductManager  {

    public long addProduct(Product product) throws NoSuchProductException;

    Product getProduct(long id) throws NoSuchProductException;
     List<Product> getAllProducts();

    Product updateProduct(long id, Product product) throws  NoSuchProductException;
    void DeleteProduct(long id ) throws NoSuchProductException;
}
