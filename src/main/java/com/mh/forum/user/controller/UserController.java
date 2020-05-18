package com.mh.forum.user.controller;

import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/create")
    public UserDto create(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }

    @CrossOrigin
    @PostMapping("/login")
    public UserDto login(@RequestHeader("Authorization") String token) {
        return userService.login(token);
    }


}
