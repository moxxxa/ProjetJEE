package com.mh.forum.dao;


import com.mh.forum.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    //String find(String userEmail);



}
