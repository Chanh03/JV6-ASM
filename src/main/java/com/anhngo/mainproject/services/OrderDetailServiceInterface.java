package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.OrderDetail;

import java.util.List;

public interface OrderDetailServiceInterface {
    List<OrderDetail> findAllByOrderId(int id);
}
