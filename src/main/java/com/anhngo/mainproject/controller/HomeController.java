package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.services.CategoryService;
import com.anhngo.mainproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("listCategory", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "home/home";
    }

    @RequestMapping("/search")
    public String searchByCategoryId(Model model,
                                     @RequestParam(name = "id", required = false, defaultValue = "0") String categoryId,
                                     @RequestParam(name = "min", required = false, defaultValue = "0") double min,
                                     @RequestParam(name = "max", required = false, defaultValue = "0") double max) {
        if (categoryId.equals("0") && min == 0 && max == 0) {
            return "redirect:/";
        }

        min = (min == 0) ? Double.MIN_VALUE : min;
        max = (max == 0) ? Double.MAX_VALUE : max;

        if (min > max) {
            double temp = min;
            min = max;
            max = temp;
        }

        model.addAttribute("products", categoryId.equals("0")
                ? productService.getProductsByPriceBetween(min, max)
                : productService.getProductsByCategory(categoryId, min, max));

        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "home/home";
    }
}
