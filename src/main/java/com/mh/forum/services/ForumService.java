package com.mh.forum.services;

import com.mh.forum.dto.AddCommentDto;
import com.mh.forum.dto.AddPostDto;
import com.mh.forum.dto.CommentDto;
import com.mh.forum.dto.PostDto;
import com.mh.forum.entity.Category;
import com.mh.forum.entity.Post;

import java.util.List;
import java.util.stream.Stream;


public interface ForumService {


    PostDto addPost(AddPostDto addPostDto, String creator);
    /*  CommentDto addC(AddCommentDto addCommentDto, String creator);*/

    PostDto addComment(String id, AddCommentDto addCommentDto, String creator);

    PostDto getPost(String id);

    Iterable<PostDto> getPostsByUser(String creator);

    Iterable<PostDto> getPosts();

    Iterable<PostDto> getPostsByCategory(String category);

    Iterable<CommentDto> getCommentsByPost(String id);

    Iterable<CommentDto> getCommentsByUser(String creator);

    int getLikesByPost(String  id);
    PostDto deletePost(String id);

    PostDto updatePost(AddPostDto updatePostDto, String id);

    boolean addLike(String id);

    //Category addCategory(Category category);

    List<Category> getCategories();


}
