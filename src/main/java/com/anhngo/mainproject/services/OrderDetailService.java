package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.OrderDetail;
import com.anhngo.mainproject.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements OrderDetailServiceInterface {
    @Autowired
    private OrderDetailRepo orderDetailRepo;


    @Override
    public List<OrderDetail> findAllByOrderId(int id) {
        return orderDetailRepo.findAllByOrderId(id);
    }
}
