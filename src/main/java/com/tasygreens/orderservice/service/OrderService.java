package com.tasygreens.orderservice.service;

import com.tasygreens.orderservice.model.Order;

public interface OrderService {

    public String processOrder(Order order);

    public Order fetchOrderById(String id);

}
