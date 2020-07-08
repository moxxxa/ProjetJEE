package com.mh.forum.user.controller;

import com.mh.forum.post.dto.AddPostDto;
import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.PasswordDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;


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
        return userService.login(token);
    }

    @PostMapping("/update/{idUser}")
    public UserDto updateUser(@RequestBody UserDto userChange, @PathVariable("idUser") String idUser) { return userService.updateUser(userChange, idUser); }

    @PostMapping("/update-password/{idUser}")
    public ResponseEntity updateUserPassword(@RequestBody PasswordDto newPassword, @PathVariable("idUser") String idUser) {
        Boolean result = userService.updateUserPassword(newPassword, idUser);
        return new ResponseEntity((result == true) ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
    }
}
