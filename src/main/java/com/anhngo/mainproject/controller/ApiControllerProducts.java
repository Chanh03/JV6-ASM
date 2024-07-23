package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Product;
import com.anhngo.mainproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ApiControllerProducts {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Iterable<Product> index() {
        return productService.getAllProduct();
    }

    @PostMapping
    public void add(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Product product, @PathVariable(name = "id") int id) {
        if (productService.existsById(id)) {
            productService.updateProduct(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        if (productService.existsById(id)) {
            productService.deleteProductById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/{id}")
    public Product edit(@PathVariable(name = "id") int id) {
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            throw new RuntimeException("Product not found");
        }
    }
}
