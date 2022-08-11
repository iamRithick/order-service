package com.tasygreens.orderservice.service.impl;

import com.tasygreens.orderservice.model.DeliveryStatus;
import com.tasygreens.orderservice.model.Order;
import com.tasygreens.orderservice.repo.OrderRepository;
import com.tasygreens.orderservice.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Override
    public String processOrder(Order order) {
        order.setOrderDate(new Date());
        order.setDeliveryStatus(DeliveryStatus.ORDERED);
        Order savedOrder = null;
        try {
            savedOrder = orderRepository.save(order);
            return savedOrder.getId();
        } catch (Exception e){
            LOGGER.error("Exception while persisting Order: ", e);
            return null;
        }
    }

    @Override
    public Order fetchOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
}
