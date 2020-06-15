package com.mh.forum.like.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mh.forum.comment.dto.CommentDto;
import com.mh.forum.post.model.Post;
import com.mh.forum.user.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto {
    User user;
    //Post post;
}
