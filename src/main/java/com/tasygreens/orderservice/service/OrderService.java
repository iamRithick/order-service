package com.tasygreens.orderservice.service;

import com.tasygreens.orderservice.model.Order;

public interface OrderService {

    public Long processOrder(Order order);

    public Order fetchOrderById(Long id);

}
