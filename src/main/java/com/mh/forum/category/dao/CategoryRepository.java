package com.mh.forum.category.dao;

import com.mh.forum.category.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository <Category, String>  {
    
}
