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
    String owner;
    String idUser;
    String creator;
    @Setter
    String content;
    //@DBRef
    Post post;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate;


    public Comment(String idUser, String creator, String content, String owner) {
        this.idUser = idUser;
        this.content = content;
        this.creator = creator;
        dateCreate = LocalDateTime.now();
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
