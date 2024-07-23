package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServiceInterface {
    void saveProduct(Product product);

    Product getProductById(int id);

    void deleteProductById(int id);

    Page<Product> getAllProductsByPage(Pageable pageable);

    List<Product> getAllProduct();

    void updateProduct(Product product);

    Iterable<Product> getProductsByCategory(String id, double min, double max);

    Iterable<Product> getProductsByPriceBetween(double min, double max);

    Iterable<Product> getProductsByPrice(double price);

    Iterable<Product> getAllProductsAvailable(boolean available);

    boolean existsById(int id);
}
