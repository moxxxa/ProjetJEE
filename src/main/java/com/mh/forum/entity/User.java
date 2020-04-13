package com.mh.forum.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


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
   // @Id
    @NotBlank
    //@Size(max = 120)
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
    // byte[] profilePicture;
/*    Set<Comment> comments = new HashSet<Comment>(0);
    Set<Post> posts = new HashSet<Post>(0);*/


}
