package com.tasygreens.orderservice.controller;

import com.tasygreens.orderservice.model.Order;
import com.tasygreens.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<Map<String, String>> placeOrder(@Validated @RequestBody Order order){
        Map<String, String> resultMap = new HashMap<>();
        ResponseEntity<Map<String, String>> response = new ResponseEntity<>(resultMap, HttpStatus.OK);
        Long id = orderService.processOrder(order);
        if (id != null){
            resultMap.put("order_id", id.toString());
        } else {
            response = new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
            resultMap.put("error", "Unable to process the order.");
        }
        return response;
    }

    @GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> fetchOrder(@PathVariable("orderId") Long orderId){
        ResponseEntity<Object> orderResponse;
        Order order = orderService.fetchOrderById(orderId);
        if (order != null){
            orderResponse = new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", String.format("Order with id %d not found", orderId));
            orderResponse = new ResponseEntity<>(errorMap, HttpStatus.OK);
        }
        return orderResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> errorMap.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return errorMap;
    }

}
