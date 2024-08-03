package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.services.CategoryService;
import com.anhngo.mainproject.services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ProductServiceInterface productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping()
    public String home(Model model) {
        //CHECK ROLE
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String fullname = ((Account) userDetails).getFullname();
            model.addAttribute("username", fullname);
        }
        model.addAttribute("listCategory", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProduct());
        return "home/home";
    }

    @RequestMapping("/search")
    public String searchByCategoryId(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") String categoryId, @RequestParam(name = "min", required = false, defaultValue = "0") double min, @RequestParam(name = "max", required = false, defaultValue = "0") double max) {
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

        model.addAttribute("products", categoryId.equals("0") ? productService.getProductsByPriceBetween(min, max) : productService.getProductsByCategory(categoryId, min, max));
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "home/home";
    }

    @RequestMapping("/product-detail/{id}")
    public String productDetail(Model model, @PathVariable(name = "id") Integer id) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("spCungLoai", productService.getProductById(id).getCategory().getProducts());
        return "home/productDetail";
    }

    @RequestMapping("/cart-detail")
    public String cartDetail() {
        return "home/cartDetail";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }
}
