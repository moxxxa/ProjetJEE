package com.mh.forum.user.services;

import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.PasswordDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserDto addUser(AddUserDto addUserDto);

    UserDto login(String idUser);

    UserDto deleteUser(String login);

    UserDto updateUser(UserDto userChange, String id);

    boolean updateUserPassword(PasswordDto newPassword, String id);


}
