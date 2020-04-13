package com.mh.forum.services;

import com.mh.forum.dto.AddUserDto;
import com.mh.forum.dto.UserDto;

public interface UserService {

    UserDto addUser(AddUserDto addUserDto);

    UserDto login(String idUser);

    UserDto deleteUser(String login);


}
