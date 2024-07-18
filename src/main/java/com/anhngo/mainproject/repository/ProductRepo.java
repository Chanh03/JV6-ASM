package com.anhngo.mainproject.repository;

import com.anhngo.mainproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category.id = :id and p.price >= :min and p.price <= :max")
    List<Product> findByCategory(String id, double min, double max);

    List<Product> findByPriceBetween(double min, double max);

    Iterable<Product> findByPrice(double price);

    List<Product> findByAvailable(boolean b);
}
