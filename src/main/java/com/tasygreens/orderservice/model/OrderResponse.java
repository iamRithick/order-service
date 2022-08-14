package com.tasygreens.orderservice.model;

import lombok.Data;

@Data
public class OrderResponse {

    private Order order;

    private String[] errors;
}
