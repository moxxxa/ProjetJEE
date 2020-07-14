package com.mh.forum.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String idUser;
    private String email;
    private String firstName;
    private String lastName;
    private String token;

    public String getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getToken() { return token; }

}