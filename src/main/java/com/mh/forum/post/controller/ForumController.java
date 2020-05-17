package com.mh.forum.post.controller;


import com.mh.forum.category.model.Category;
import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.post.dto.AddPostDto;
import com.mh.forum.post.dto.PostDto;
import com.mh.forum.post.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    ForumService forumService;

    @CrossOrigin
    @PostMapping("/post/{creator}")
    //@PreAuthorize("#author==authentication.name")
    public PostDto addPost(@RequestBody AddPostDto addPost, @PathVariable("creator") String author) {
        return forumService.addPost(addPost, author);
    }

    @CrossOrigin
    @PutMapping("/post/{id}/comment/{creator}")
    public PostDto addComment(@PathVariable String id, @PathVariable String creator, @RequestBody AddCommentDto addCommentDto) {
        return forumService.addComment(id, addCommentDto, creator);
    }

    @CrossOrigin
    @GetMapping("/post/{id}")
    public PostDto getPost(@PathVariable String id) {
        return forumService.getPost(id);
    }

    @CrossOrigin
    @GetMapping("/posts")
    public Iterable<PostDto> getPosts() {
        return forumService.getPosts();
    }

    @CrossOrigin
    @DeleteMapping("/post/{id}")
    public PostDto deletePost(@PathVariable String id) {
        return forumService.deletePost(id);
    }

    @CrossOrigin
    @PutMapping("/post/{id}")
    public PostDto updatePost(@RequestBody AddPostDto postUpdateDto, @PathVariable String id) {
        return forumService.updatePost(postUpdateDto, id);
    }

    @CrossOrigin
    @PutMapping("/post/{id}/like")
    public boolean addLike(@PathVariable String id) {
        return forumService.addLike(id);
    }

    @CrossOrigin
    @PutMapping("/post/{id}/dislike")
    public boolean dislike(@PathVariable String id) {
        return forumService.addLike(id);
    }

    @CrossOrigin
    @GetMapping("/posts/creator/{creator}")
    public Iterable<PostDto> findPostsByCreator(@PathVariable String creator) {
        return forumService.getPostsByUser(creator);
    }

    @CrossOrigin
    @GetMapping("/posts/category/{category}")
    public Iterable<PostDto> findPostsByCategory(@PathVariable String category) {
        return forumService.getPostsByCategory(category);
    }

//    @CrossOrigin
//    @GetMapping("/post/{id}/comments")
//    public Iterable<CommentDto> getCommentsByPost(@PathVariable String id) {
//        return forumService.getCommentsByPost(id);
//    }

    //getLikesByPost
    @CrossOrigin
    @GetMapping("/post/{id}/likes")
    public int getLikesByPost(@PathVariable String id) {
        return forumService.getLikesByPost(id);
    }

//    @CrossOrigin
//    @GetMapping("/comments/creator/{creator}")
//    public Iterable<CommentDto> getCommentsByCreator(@PathVariable String creator) {
//        return forumService.getCommentsByUser(creator);
//    }


 /*  @PostMapping("/comment/{creator}")
    //@PreAuthorize("#author==authentication.name")
    public CommentDto addC(@RequestBody AddCommentDto addC, @PathVariable("creator") String author) {
        return forumService.addC(addC, author);
    }*/
}
