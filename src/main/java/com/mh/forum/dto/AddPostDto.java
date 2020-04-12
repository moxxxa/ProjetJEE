package com.mh.forum.dto;

import lombok.Builder;
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
