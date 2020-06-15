package com.mh.forum.like.controller;


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
public class LikeController {


//    @CrossOrigin
//    @PutMapping("/post/{id}/like")
//    public boolean addLike(@PathVariable String id) {
//        return forumService.addLike(id);
//    }
//
//    @CrossOrigin
//    @PutMapping("/post/{id}/dislike")
//    public boolean dislike(@PathVariable String id) {
//        return forumService.addLike(id);
//    }
//
//
//
//    //getLikesByPost
//    @CrossOrigin
//    @GetMapping("/post/{id}/likes")
//    public int getLikesByPost(@PathVariable String id) {
//        return forumService.getLikesByPost(id);
//    }

}
