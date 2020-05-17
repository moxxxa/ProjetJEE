package com.mh.forum.comment.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.post.model.Post;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "comments")
public class Comment {
    //@Id
    //String idComment;
    String userEmail;
    @Setter
    String content;
    //@DBRef
    Post post;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate;


    public Comment(String userEmail, String content) {
        this.userEmail = userEmail;
        this.content = content;
        dateCreate = LocalDateTime.now();
    }
}
