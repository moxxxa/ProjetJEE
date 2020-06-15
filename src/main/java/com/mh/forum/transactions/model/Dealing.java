package com.mh.forum.transactions.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "transactions")
public class Dealing {

    @Id
    String idTransaction;
    Debtor debtor;
    Creditor creditor;
    String dateCreate;
    Sum sum;

    public Dealing(Debtor debtor, Creditor creditor, String dateCreate, Sum sum) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.dateCreate = dateCreate;
        this.sum = sum;
    }
}
