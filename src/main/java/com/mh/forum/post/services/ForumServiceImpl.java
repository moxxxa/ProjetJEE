package com.mh.forum.post.services;

import com.mh.forum.category.dao.CategoryRepository;
import com.mh.forum.category.model.Category;
import com.mh.forum.comment.dao.CommentRepositry;
import com.mh.forum.comment.dto.AddCommentDto;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.comment.model.Comment;
import com.mh.forum.exceptions.PostNotFoundException;
import com.mh.forum.like.dao.LikeRepository;
import com.mh.forum.like.dto.LikeDto;
import com.mh.forum.like.model.Like;
import com.mh.forum.post.dao.ForumRepository;
import com.mh.forum.post.dto.AddPostDto;
import com.mh.forum.post.dto.PostDto;
import com.mh.forum.post.model.Post;
import com.mh.forum.user.dao.UserRepository;
import com.mh.forum.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForumServiceImpl implements ForumService {


    @Autowired
    ForumRepository forumRepository;
    @Autowired
    CommentRepositry commentRepositry;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;


    @Override
    public PostDto addPost(AddPostDto addPostDto, String creator, String idUser) {
        Post post = new Post(addPostDto.getSubject(),
                addPostDto.getContent(),
                addPostDto.getCategory(),
                creator,
                idUser);
        //Post post = new Post(creator,idUser, addPostDto.getSubject(), addPostDto.getContent(), addPostDto.getCategory(), addPostDto.getName());
        post = forumRepository.save(post);
        return convertToPostDto(post);
    }

    @Override
    public PostDto addComment(String id, AddCommentDto addCommentDto, String creator, String idUser, String owner) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        System.out.println("creator =" +  creator);
        Comment comment = new Comment(idUser, creator, addCommentDto.getContent(), owner);
        post.addComment(comment);
        forumRepository.save(post);
        return convertToPostDto(post);
    }

    @Override
    public PostDto getPost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return convertToPostDto(post);
    }

    @Override
    public Iterable<PostDto> getPostsByUser(String creator) {
        return forumRepository.findPostsByIdUser(creator)
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PostDto> getPostsByCategory(String category) {
        return forumRepository.findPostsByCategory(category)
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PostDto> getPosts() {
        return forumRepository.findAllByOrderByDateCreateDesc()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }


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

    @Override
    public int getLikesByPost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        int likes = post.getLikesCount();
        return likes;
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        forumRepository.delete(post);
        return convertToPostDto(post);
    }

    @Override
    public PostDto updatePost(AddPostDto updatePostDto, String id) {
        Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        String content = updatePostDto.getContent();

        if (null != content) {
            post.setContent(content);
        }
        String subject = updatePostDto.getSubject();
        if (null != content) {
            post.setSubject(subject);
        }
        forumRepository.save(post);
        return convertToPostDto(post);
    }

    @Override
    public boolean addLike(String idPost, String idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        Post post = forumRepository.findById(idPost).orElse(null);
        List<Like> likes = post.getLikes();
        if (!likes.isEmpty()) {
            for (int i = 0; i < likes.size(); i++) {
                if (likes.get(i).getUser().getIdUser().equals(user.getIdUser())) {
                    return false;
                }
            }
        }
        Like like = new Like(user);
        likeRepository.save(like);//save like
        post.addLike();// increment likesCount
        post.addLikes(like);// add like to this post
        forumRepository.save(post);//save post
        return true;
    }

    @Override
    public boolean dislike(String id) {

        Post post = forumRepository.findById(id).orElse(null);
        if (null != post) {
            post.dislike();
            forumRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


    private PostDto convertToPostDto(Post post) {
        return PostDto.builder()
                .idPost(post.getIdPost())
                .idUser(post.getIdUser())
                .subject(post.getSubject())
                .creator(post.getCreator())
                .dateCreate(post.getDateCreate())
                .content(post.getContent())
                .likesCount(post.getLikesCount())
                .comments(post.getComments().stream().map(this::convertToCommentDto).collect(Collectors.toList()))
                .category(post.getCategory())
                .likes(post.getLikes().stream().map(this::convertToLikeDto).collect(Collectors.toList()))
                .build();
    }

    private CommentDto convertToCommentDto(Comment comment) {
        return CommentDto.builder().idUser(comment.getIdUser()).content(comment.getContent())
                .dateCreate(comment.getDateCreate()).owner(comment.getOwner()).build();
    }

    private LikeDto convertToLikeDto(Like like) {
        return LikeDto.builder().user(like.getUser()).build();
    }

}
