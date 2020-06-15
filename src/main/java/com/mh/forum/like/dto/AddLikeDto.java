package com.mh.forum.like.dto;//package com.mh.forum.like.dto;

import com.mh.forum.post.model.Post;
import com.mh.forum.user.model.User;
import lombok.Getter;

//@Builder
@Getter
public class AddLikeDto {
    User user;
    Post post;
}
