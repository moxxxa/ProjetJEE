package com.mh.forum.comment.dao;


import com.mh.forum.comment.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;

public interface CommentRepositry extends MongoRepository<Comment, String> {

    Stream<Comment> findCommentByUserEmail(String user);
}
