package com.mh.forum.user.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonSerialize(using = ToStringSerializer.class)
@Document(collection = "users")
public class User {

    @Id
    String idUser;

    @NotBlank
    String email;

    public String getPassword() {
        return password;
    }

    @NotBlank
    //@Size(max = 120)
            String password;

    @NotBlank
    // @Size(max = 120)
            String firstName;
    @NotBlank
    //@Size(max = 120)
            String lastName;
    // byte[] profilePicture;
/*    Set<Comment> comments = new HashSet<Comment>(0);
    Set<Post> posts = new HashSet<Post>(0);*/


}
