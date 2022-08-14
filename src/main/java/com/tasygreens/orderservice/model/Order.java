package com.tasygreens.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "Order")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "ORDER_SEQ";

    @Id
    private Long orderId;

    @NotBlank(message = "Username/Email cannot be empty.")
    private String user;

    private String amount;

    @NotNull(message = "Items cannot be empty.")
    private List<Item> items;

    private Date orderDate;

    private DeliveryStatus deliveryStatus;

}
