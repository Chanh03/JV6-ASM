package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Product;
import com.anhngo.mainproject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepo.findById(id).get();
    }

    @Override
    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Page<Product> getAllProductsByPage(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }


    @Override
    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public Iterable<Product> getProductsByCategory(String id, double min, double max) {
        return productRepo.findByCategoryAndPriceBetween(id, min, max);
    }

    @Override
    public Iterable<Product> getProductsByPriceBetween(double min, double max) {
        return productRepo.findByPriceBetween(min, max);
    }

    @Override
    public Iterable<Product> getProductsByPrice(double price) {
        return productRepo.findByPrice(price);
    }

    @Override
    public Iterable<Product> getAllProductsAvailable(boolean available) {
        return productRepo.findByAvailable(available);
    }

    @Override
    public boolean existsById(int id) {
        return productRepo.existsById(id);
    }
}