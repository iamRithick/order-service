package com.tasygreens.orderservice.repo;

import com.tasygreens.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {

}
