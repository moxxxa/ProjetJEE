package com.mh.forum.user.services;

import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.PasswordDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.model.User;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    UserDto addUser(AddUserDto addUserDto) throws NoSuchAlgorithmException;

    UserDto login(String idUser) throws NoSuchAlgorithmException;

    UserDto deleteUser(String login);

    UserDto updateUser(UserDto userChange, String token);

    UserDto updateUserPassword(PasswordDto newPassword, String token);

    UserDto findUserByToken(String token);


}
