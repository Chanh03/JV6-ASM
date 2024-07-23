package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Category;
import com.anhngo.mainproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categories")
public class ApiControllerCategories {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public Iterable<Category> index() {
        return categoryService.getAllCategories();
    }
}
