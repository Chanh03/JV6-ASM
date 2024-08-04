package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Order;
import com.anhngo.mainproject.entities.OrderDetail;
import com.anhngo.mainproject.repository.OrderDetailRepo;
import com.anhngo.mainproject.repository.OrderRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public Iterable<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        orderRepo.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        List<OrderDetail> orderDetails = mapper.convertValue(orderData.get("orderDetails"), type).stream().peek(orderDetail -> orderDetail.setOrder(order)).collect(Collectors.toList());
        orderDetailRepo.saveAll(orderDetails);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepo.findById(id).get();
    }

}
