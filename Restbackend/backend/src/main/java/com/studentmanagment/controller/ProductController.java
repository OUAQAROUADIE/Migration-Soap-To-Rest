package com.studentmanagment.controller;

import com.studentmanagment.service.ProductManager;
import com.studentmanagment.exception.NoSuchProductException;
import com.studentmanagment.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductManager productService;

    @PostMapping
    public long addProduct(@RequestBody Product product) throws NoSuchProductException {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) throws NoSuchProductException {
        return productService.getProduct(id);
    }
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @DeleteMapping("/{id}")
    public void DeleteProduct(@PathVariable long id ) throws NoSuchProductException{
        productService.DeleteProduct(id);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) throws NoSuchProductException {
        return productService.updateProduct(id, product);
    }

}
