package com.mh.forum.user.dao;


import com.mh.forum.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    //String find(String userEmail);
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);


}
