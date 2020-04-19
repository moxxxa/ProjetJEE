package com.mh.forum.controller;

import com.mh.forum.configuration.UserConfig;
import com.mh.forum.dto.AddPostDto;
import com.mh.forum.dto.AddUserDto;
import com.mh.forum.dto.UserDto;
import com.mh.forum.services.ForumService;
import com.mh.forum.services.UserService;
import com.mh.forum.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserDto create(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestHeader("Authorization") String token) {
        return userService.login(token );
    }
/*    @PostMapping("/login")
    public UserDto login(Authentication authentication) {
        return userService.login(authentication.getName());
    }*/


}
