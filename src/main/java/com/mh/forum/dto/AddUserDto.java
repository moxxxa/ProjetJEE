package com.mh.forum.dto;

import lombok.Getter;

@Getter
public class AddUserDto {
     String email;
     String password;
     String firstName;
     String lastName;
     //byte[] profilePicture;
}
