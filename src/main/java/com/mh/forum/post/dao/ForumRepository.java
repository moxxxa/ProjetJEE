package com.mh.forum.post.dao;


import com.mh.forum.like.model.Like;
import com.mh.forum.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ForumRepository extends MongoRepository<Post,String> {


    Stream<Post> findAllByOrderByDateCreateDesc();
   //Stream<Post> findPostsByUserEmail(String userEmail);
    Stream<Post> findPostsByIdUser(String idUser);
    Stream<Post> findPostsByCategory(String category);


    // public List<Post> findByUserEmailId(String userEmailId);
/*    Stream<Post> findbyUser(String user);
    List<Post> findAllByOrderByDatePost();
    List<Post> findPostsBySubject(String subject);*/
}
