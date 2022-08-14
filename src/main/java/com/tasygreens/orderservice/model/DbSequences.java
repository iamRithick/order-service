package com.tasygreens.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "db_sequences")
public class DbSequences {

    @Id
    private String id;
    private Long seq;

}
