package com.mh.forum.transactions.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "amounts")
public class Sum {

    @Id
    String idAmount;
    String currency;
    String total;

    public Sum(String currency, String total) {
        this.currency = currency;
        this.total = total;
    }
}
