package com.mh.forum.post.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.comment.model.Comment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "posts")
public class Post {
    @Id
    String idPost;
    String userEmail;
    @Setter
    String subject;
    @Setter
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate;
    int likes;
    Set<Comment> comments;
    //User creator;
    @Setter
    String category;

    public Post(String userEmail, String subject, String content, String category) {
        this.userEmail = userEmail;
        this.subject = subject;
        this.content = content;
        this.dateCreate = LocalDateTime.now();
        comments = new HashSet<Comment>(0);
        this.category = category;
    }

    public void addLike() {
        likes++;
    }

    public void dislike() {
        likes--;
    }

    public boolean addComment(Comment comment) {

        return comments.add(comment);
    }
}
