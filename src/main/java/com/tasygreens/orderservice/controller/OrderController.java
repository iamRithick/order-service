package com.tasygreens.orderservice.controller;

import com.tasygreens.orderservice.model.Order;
import com.tasygreens.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String hello(){
        return "Hello from Order Service.";
    }

    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> placeOrder(@RequestBody Order order){
        Map<String, String> resultMap = new HashMap<>();
        ResponseEntity<Map<String, String>> response = new ResponseEntity<>(resultMap, HttpStatus.OK);
        String id = orderService.processOrder(order);
        if (id != null){
            resultMap.put("order_id", id);
        } else {
            response = new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
            resultMap.put("error", "Unable to process the order.");
        }
        return response;
    }

    @GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> fetchOrder(@PathVariable("orderId") String orderId){
        Order order = new Order();
        order.setId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
