package com.tasygreens.orderservice.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Item {

    @NotBlank(message = "Item name cannot be empty.")
    private String title;

    @NotBlank(message = "Item quantity cannot be empty.")
    private int quantity;

    @NotBlank(message = "Item description cannot be empty.")
    private String description;

    @NotBlank(message = "Item price cannot be empty.")
    private float price;
}
