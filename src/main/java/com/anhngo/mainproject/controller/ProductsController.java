package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Product;
import com.anhngo.mainproject.services.CategoryService;
import com.anhngo.mainproject.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("serverUrl")
    public String serverUrl(HttpServletRequest request) {
        return request.getServletPath();
    }

    @RequestMapping({"", "/edit/{id}"})
    public String index(Model model, @PathVariable(name = "id", required = false) Integer id) {
        if (id != null) {
            model.addAttribute("product", productService.getProductById(id));
        } else {
            model.addAttribute("product", new Product());
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProduct());
        return "admin/products";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Product product) {
        if (product.getCreateDate() == null) {
            product.setCreateDate(new Date());
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @RequestMapping("/filter")
    public String filter(Model model, @ModelAttribute Product product, @RequestParam(name = "availableFilter", required = false) Boolean availableFilter) {
        if (availableFilter == null) {
            model.addAttribute("products", productService.getAllProduct());
        } else {
            model.addAttribute("products", productService.getAllProductsAvailable(availableFilter));
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/products";
    }
}