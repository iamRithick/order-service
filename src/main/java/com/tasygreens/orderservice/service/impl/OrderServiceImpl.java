package com.tasygreens.orderservice.service.impl;

import com.tasygreens.orderservice.model.DeliveryStatus;
import com.tasygreens.orderservice.model.Item;
import com.tasygreens.orderservice.model.Order;
import com.tasygreens.orderservice.repo.OrderRepository;
import com.tasygreens.orderservice.service.MongoDBSequenceGenerator;
import com.tasygreens.orderservice.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MongoDBSequenceGenerator sequenceGenerator;

    @Override
    public Long processOrder(Order order) {
        if (!StringUtils.hasLength(order.getAmount())){
            float totalAmount = 0;
            for (Item item : order.getItems()){
                totalAmount += item.getPrice();
            }
            order.setAmount(String.valueOf(totalAmount));
        }
        order.setOrderDate(new Date());
        order.setDeliveryStatus(DeliveryStatus.ORDERED);
        Order savedOrder = null;
        try {
            order.setOrderId(sequenceGenerator.generateSequenceNumber(Order.SEQUENCE_NAME));
            savedOrder = orderRepository.save(order);
            return savedOrder.getOrderId();
        } catch (Exception e){
            LOGGER.error("Exception while persisting Order: ", e);
            return null;
        }
    }

    @Override
    public Order fetchOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
}
