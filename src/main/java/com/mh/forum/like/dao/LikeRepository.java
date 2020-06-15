package com.mh.forum.like.dao;


import com.mh.forum.like.model.Like;
import com.mh.forum.post.model.Post;
import com.mh.forum.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {

    //Stream<Post> findPostsByUserEmail(String userEmail);
    //Like findLikeByUser(User user);
}
