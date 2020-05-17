package com.mh.forum.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.comment.dto.CommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostDto {
    String idPost;
    String userEmail;
    String subject;
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime dateCreate = LocalDateTime.now();
    Integer likes;
    @Singular
    List<CommentDto> comments;
    String category;
}
