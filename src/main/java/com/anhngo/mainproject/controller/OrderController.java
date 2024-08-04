package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.services.OrderDetailServiceInterface;
import com.anhngo.mainproject.services.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceInterface orderServiceInterface;

    @Autowired
    private OrderDetailServiceInterface orderDetailServiceInterface;

    @ModelAttribute
    public void addAttributes(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("username", account.getFullname());
    }

    @RequestMapping("/checkout")
    public String orderCheckout(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("purchaser", account.getUsername());
        return "home/check-out/order-checkout";
    }

    @RequestMapping("/history")
    public String orderhistory(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("orderHistory", orderServiceInterface.findAll().reversed());
        return "home/check-out/order-history";
    }

    @RequestMapping("/success/{id}")
    public String orderSuccess(@PathVariable int id, Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("orders", orderServiceInterface.findById(id));
        model.addAttribute("orderDetails", orderDetailServiceInterface.findAllByOrderId(id));
        return "home/check-out/order-success";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable(name = "id") Integer id, @AuthenticationPrincipal Account account) {
        model.addAttribute("order", orderServiceInterface.findById(id));
        model.addAttribute("orderDetails", orderDetailServiceInterface.findAllByOrderId(id));
        return "home/check-out/order-details";
    }
}
