package com.mh.forum.category.services;

import com.mh.forum.category.dao.CategoryRepository;
import com.mh.forum.category.model.Category;
import com.mh.forum.comment.dao.CommentRepositry;
import com.mh.forum.post.dao.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ForumRepository forumRepository;
    @Autowired
    CommentRepositry commentRepositry;
    @Autowired
    CategoryRepository categoryRepository;


    /*@Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }*/

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


}
