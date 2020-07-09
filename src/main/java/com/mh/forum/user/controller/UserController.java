package com.mh.forum.user.controller;

import com.mh.forum.mailNotifyer.MailSender.Sender;
import com.mh.forum.mailNotifyer.MessageFactory;
import com.mh.forum.mailNotifyer.SubjectFactory;
import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.PasswordDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    Sender sender;

    @PostMapping("/create")
    public UserDto create(@RequestBody AddUserDto addUserDto) {
        UserDto createdUser = userService.addUser(addUserDto);
        try {
            sender.sendMail(createdUser.getEmail(), MessageFactory.welcomeMessage(), SubjectFactory.subject("Subscription"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return createdUser;
    }

    @PostMapping("/login")
    public UserDto login(@RequestHeader("Authorization") String token) {
        return userService.login(token);
    }

    @PostMapping("/update/{idUser}")
    public UserDto updateUser(@RequestBody UserDto userChange, @PathVariable("idUser") String idUser) {
        UserDto updatedUser = userService.updateUser(userChange, idUser);
        try {
            sender.sendMail(updatedUser.getEmail(), MessageFactory.accountUpdatedMessage(), SubjectFactory.subject("Personnal informations"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userService.updateUser(userChange, idUser);
    }

    @PostMapping("/update-password/{idUser}")
    public ResponseEntity updateUserPassword(@RequestBody PasswordDto newPassword, @PathVariable("idUser") String idUser) {
        Boolean result = userService.updateUserPassword(newPassword, idUser);
        if (result) {
            try {
                sender.sendMail(idUser, MessageFactory.passwordChangeMessage(), SubjectFactory.subject("Password"));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity((result == true) ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
    }
}
