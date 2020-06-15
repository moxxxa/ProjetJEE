package com.mh.forum.like.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.post.model.Post;
import com.mh.forum.user.model.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
//@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Likes")
public class Like {
    @Id
    String idLike;
    User user;

    public Like(User user) {
        this.user = user;
    }


}
