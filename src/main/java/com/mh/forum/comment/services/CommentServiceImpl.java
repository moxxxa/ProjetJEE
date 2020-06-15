package com.mh.forum.comment.services;

import com.mh.forum.comment.dao.CommentRepositry;
import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.post.dao.ForumRepository;
import com.mh.forum.post.dto.PostDto;
import com.mh.forum.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    ForumRepository forumRepository;
    @Autowired
    CommentRepositry commentRepositry;

  /*  @Override
    public CommentDto addC(AddCommentDto addCommentDto, String creator) {
        Comment comment = new Comment(creator, addCommentDto.getContent());
        comment = commentRepositry.save(comment);
        return convertToCommentDto(comment);
    }*/

//    @Override
//    public PostDto addComment(String id, AddCommentDto addCommentDto,String creator, String idUser){
//        //public PostDto addComment(String id, AddCommentDto addCommentDto, String creator) {
//        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
//        Comment comment = new Comment(idUser,creator, addCommentDto.getContent(), addCommentDto.getOwner());
//        post.addComment(comment);
//        forumRepository.save(post);
//        return convertToPostDto(post);
//    }


    @Override
    public Iterable<CommentDto> getCommentsByPost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        Set<Comment> comments = post.getComments();
        return comments.stream().map(this::convertToCommentDto).collect(Collectors.toList());
    }

    @Override
    public Iterable<CommentDto> getCommentsByUser(String idUser) {
        return commentRepositry.findCommentByIdUser(idUser)
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

    /*
        @Override
        public Iterable<CommentDto> getLikesByPost(String id) {
            Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
            Set<Comment> comments = post.getComments();
            return comments.stream().map(this::convertToCommentDto).collect(Collectors.toList());
        }*/


//    @Override
//    public boolean addLike(String id) {
//
//        Post post = forumRepository.findById(id).orElse(null);
//        if (null != post) {
//            post.addLike();
//            forumRepository.save(post);
//            return true;
//        }
//        return false;
//    }

    /*@Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }*/

    private CommentDto convertToCommentDto(Comment comment) {
        return CommentDto.builder().idUser(comment.getIdUser()).content(comment.getContent()).owner(comment.getOwner())
                .dateCreate(comment.getDateCreate()).build();
    }

    private PostDto convertToPostDto(Post post) {
        return PostDto.builder()
                .idPost(post.getIdPost())
                .idUser(post.getIdUser())
                .subject(post.getSubject())
                .dateCreate(post.getDateCreate())
                .content(post.getContent())
                .likesCount(post.getLikesCount())
                .comments(post.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toList()))
                .category(post.getCategory())
                .build();
    }

}
