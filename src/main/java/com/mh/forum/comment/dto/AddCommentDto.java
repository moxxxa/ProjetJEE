package com.mh.forum.comment.dto;

import lombok.Getter;

//@Builder
@Getter
public class AddCommentDto {
    String content;
    String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

  /*  public String getContent(){
        return  content;
    }*/

/*
  public String getContent(){
      return content;
  }
*/

}


