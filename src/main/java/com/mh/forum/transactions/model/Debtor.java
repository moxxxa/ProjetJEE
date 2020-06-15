package com.mh.forum.transactions.model;


import com.mh.forum.comment.model.Comment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "debtors")
public class Debtor {
    @Id
    String debtorID;
    @Setter
    String email;
    @Setter
    String first_name;
    @Setter
    String last_name;
    @Setter
    String payer_id;
    @Setter
    String country_code;

    public Debtor(String email, String firstName, String lastName, String payerId, String countryCode) {
        this.email = email;
        this.first_name = firstName;
        this.last_name = lastName;
        this.payer_id = payerId;
        this.country_code = countryCode;
    }
}
