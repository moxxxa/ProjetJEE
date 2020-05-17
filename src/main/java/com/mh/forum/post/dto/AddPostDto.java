package com.mh.forum.post.dto;

import lombok.Getter;

//@Builder
@Getter
public class AddPostDto {
     String subject;
     String content;
     String category;

  /*   public String getSubject(){
          return subject;
     }
     public String getContent(){
          return content;
     }*/
}
