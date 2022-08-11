package com.tasygreens.orderservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "Order")
public class Order {
    @Id
    private String id;

    @NonNull
    private String user;

    @NonNull
    private String amount;

    @NonNull
    private List<Item> items;

    private Date orderDate;

    private DeliveryStatus deliveryStatus;

}
