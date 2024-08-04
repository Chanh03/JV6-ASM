package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Order;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderServiceInterface {

    void saveOrder(Order order);

    Order getOrderById(int id);

    void deleteOrder(int id);

    void updateOrder(Order order);

    Iterable<Order> getAllOrders();

    Order create(JsonNode order);
}
