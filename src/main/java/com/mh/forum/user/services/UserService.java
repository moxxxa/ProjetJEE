package com.mh.forum.user.services;

import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.dto.UserLoginDto;

public interface UserService {

    UserDto addUser(AddUserDto addUserDto);

    UserDto login(UserLoginDto userLoginDto);

    UserDto deleteUser(String login);


}
