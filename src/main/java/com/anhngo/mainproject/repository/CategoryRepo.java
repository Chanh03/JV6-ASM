package com.anhngo.mainproject.repository;

import com.anhngo.mainproject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {
}
