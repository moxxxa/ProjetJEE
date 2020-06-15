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

    @PostMapping("/post/{creator}/{idUser}")
    //@PreAuthorize("#author==authentication.name")
    public PostDto addPost(@RequestBody AddPostDto addPost, @PathVariable("creator") String creator, @PathVariable("idUser") String idUser) {
        return forumService.addPost(addPost, creator, idUser);
    }

    @PutMapping("/post/{id}/comment/{creator}/{idUser}")
    public PostDto addComment(@PathVariable String id, @PathVariable String creator, @PathVariable String idUser, @RequestBody AddCommentDto addCommentDto) {
        return forumService.addComment(id, addCommentDto, creator, idUser);
    }

    @GetMapping("/post/{id}")
    public PostDto getPost(@PathVariable String id) {
        return forumService.getPost(id);
    }

    @GetMapping("/posts")
    public Iterable<PostDto> getPosts() {
        return forumService.getPosts();
    }

    @DeleteMapping("/post/{id}")
    public PostDto deletePost(@PathVariable String id) {
        return forumService.deletePost(id);
    }

    @PutMapping("/post/{id}")
    public PostDto updatePost(@RequestBody AddPostDto postUpdateDto, @PathVariable String id) {
        return forumService.updatePost(postUpdateDto, id);
    }

    @PutMapping("/post/{idPost}/{idUser}/like")
    public boolean addLike(@PathVariable String idPost, @PathVariable String idUser) {
        return forumService.addLike(idPost, idUser);
    }
//    @PutMapping("/post/{id}/like")
//    public boolean addLike(@PathVariable String id) {
//        return forumService.addLike(id);
//    }

    @PutMapping("/post/{id}/dislike")
    public boolean dislike(@PathVariable String id) {
        return forumService.dislike(id);
    }

    @GetMapping("/posts/creator/{creator}")
    public Iterable<PostDto> findPostsByCreator(@PathVariable String creator) {
        return forumService.getPostsByUser(creator);
    }

    @GetMapping("/posts/category/{category}")
    public Iterable<PostDto> findPostsByCategory(@PathVariable String category) {
        return forumService.getPostsByCategory(category);
    }

//    @GetMapping("/post/{id}/comments")
//    public Iterable<CommentDto> getCommentsByPost(@PathVariable String id) {
//        return forumService.getCommentsByPost(id);
//    }

    //getLikesByPost
    @GetMapping("/post/{id}/likes")
    public int getLikesByPost(@PathVariable String id) {
        return forumService.getLikesByPost(id);
    }

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
