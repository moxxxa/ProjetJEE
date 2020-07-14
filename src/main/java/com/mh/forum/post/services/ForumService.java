package com.mh.forum.post.services;

import com.mh.forum.category.model.Category;
import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.post.dto.AddPostDto;
import com.mh.forum.post.dto.PostDto;

import java.util.List;


public interface ForumService {


    PostDto addPost(AddPostDto addPostDto, String creator, String idUser);
    /*  CommentDto addC(AddCommentDto addCommentDto, String creator);*/

    PostDto addComment(String id, AddCommentDto addCommentDto, String creator, String idUser, String owner);

    PostDto getPost(String id);

    Iterable<PostDto> getPostsByUser(String creator);

    Iterable<PostDto> getPosts();

    Iterable<PostDto> getPostsByCategory(String category);

    Iterable<CommentDto> getCommentsByPost(String id);

    Iterable<CommentDto> getCommentsByUser(String idUser);

    int getLikesByPost(String id);
    PostDto deletePost(String id);

    PostDto updatePost(AddPostDto updatePostDto, String id);
    boolean addLike(String idPost, String idUser);
    //boolean addLike(String id);
    boolean dislike(String id);
    //Category addCategory(Category category);

    List<Category> getCategories();


}
