package com.mh.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    //String password;
    String email;
    String firstName;
    String lastName;
    //byte[] profilePicture;
/*     Set<CommentDto> comments =new HashSet<CommentDto>(0);
     Set<PostDto> posts = new HashSet<PostDto>(0);*/
}
