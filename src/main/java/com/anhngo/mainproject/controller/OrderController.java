package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/checkout")
    public String orderCheckout(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("username", account.getFullname());
        model.addAttribute("purchaser", account.getUsername());
        return "home/check-out/order-checkout";
    }
}
