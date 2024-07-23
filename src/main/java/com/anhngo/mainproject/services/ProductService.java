package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Product;
import com.anhngo.mainproject.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).get();
    }

    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public Iterable<Product> getProductsByCategory(String id, double min, double max) {
        return productRepo.findByCategory(id, min, max);
    }

    public Iterable<Product> getProductsByPriceBetween(double min, double max) {
        return productRepo.findByPriceBetween(min, max);
    }

    public Iterable<Product> getProductsByPrice(double price) {
        return productRepo.findByPrice(price);
    }

    public Iterable<Product> getAllProductsAvailable(boolean available) {
        return productRepo.findByAvailable(available);
    }

    public boolean existsById(int id) {
        return productRepo.existsById(id);
    }
}
