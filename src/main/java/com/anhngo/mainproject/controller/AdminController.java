package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.services.ProductService;
import com.anhngo.mainproject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices accountService;

    @Autowired
    private ProductService productService;


    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("totalAccounts", accountService.getAllAccounts());
        model.addAttribute("totalProducts", productService.getAllProduct());
        model.addAttribute("totalProductsAvailable", productService.getAllProductsAvailable(true));
        model.addAttribute("totalProductsNotAvailable", productService.getAllProductsAvailable(false));
        return "admin/dashboard";
    }
}
