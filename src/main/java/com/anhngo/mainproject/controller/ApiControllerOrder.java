package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Order;
import com.anhngo.mainproject.services.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order")
public class ApiControllerOrder {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public Order saveOrder(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }
}
