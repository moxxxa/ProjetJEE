package com.mh.forum.transactions.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "creditors")
public class Creditor {
    @Id
    String creditorID;
    @Setter
    String email;
    @Setter
    String accountId ;


    public Creditor(String email, String accountId) {
        this.email = email;
        this.accountId = accountId;
    }

}
