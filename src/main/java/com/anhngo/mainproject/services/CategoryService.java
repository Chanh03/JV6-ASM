package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Category;
import com.anhngo.mainproject.repository.CategoryRepo;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCategoryById(String id) {
        categoryRepo.deleteById(id);
    }

    public Category getCategoryById(String id) {
        return categoryRepo.findById(id).get();
    }

    public void updateCategory(Category category) {
        categoryRepo.save(category);
    }


}
