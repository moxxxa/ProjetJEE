package com.mh.forum.dao;

import com.mh.forum.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository <Category, String>  {
    
}
