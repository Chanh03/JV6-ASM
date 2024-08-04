package com.anhngo.mainproject.services;

import com.anhngo.mainproject.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService implements OrderDetailServiceInterface {
    @Autowired
    private OrderDetailRepo orderDetailRepo;
}
