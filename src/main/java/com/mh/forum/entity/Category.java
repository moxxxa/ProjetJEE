package com.mh.forum.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "category")
public class Category {
    @Id
    String idCtegory;
    String name;


    public Category(String name) {
        this.name = name;
    }

    /*
    * Science,
    Space,
    Technologie
    * */
}
