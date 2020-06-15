package com.mh.forum.like.services;

import com.mh.forum.category.dao.CategoryRepository;
import com.mh.forum.category.model.Category;
import com.mh.forum.comment.dao.CommentRepositry;
import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.post.dao.ForumRepository;
import com.mh.forum.post.dto.AddPostDto;
import com.mh.forum.post.dto.PostDto;
import com.mh.forum.post.model.Post;
import com.mh.forum.post.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class LikeServiceImpl implements LikeService {



}
