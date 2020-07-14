package com.mh.forum.user.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "users")
public class User {
    @Id
    String idUser;

    @NotBlank
    String email;

    @NotBlank
    //@Size(max = 120)
            String password;

    @NotBlank
    // @Size(max = 120)
            String firstName;
    @NotBlank
    //@Size(max = 120)
            String lastName;

    @NotBlank
            String token;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }


}
