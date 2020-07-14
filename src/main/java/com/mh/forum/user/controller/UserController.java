package com.mh.forum.user.controller;

import com.mh.forum.configuration.UserConfig;
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
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    UserService userService;

    @Autowired
    Sender sender;

    @PostMapping("/create")
    public UserDto create(@RequestBody AddUserDto addUserDto) throws NoSuchAlgorithmException {
        UserDto createdUser = userService.addUser(addUserDto);
        try {
            sender.sendMail(createdUser.getEmail(), MessageFactory.welcomeMessage(), SubjectFactory.subject("Subscription"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return createdUser;
    }

    @PostMapping("/login")
    public UserDto login(@RequestHeader("Authorization") String token) throws NoSuchAlgorithmException{
        return userService.login(token);
    }

    @PostMapping("/update")
    public UserDto updateUser(@RequestBody UserDto userChange, @RequestHeader("Authorization") String token) {
        UserDto updatedUser = userService.updateUser(userChange, userConfig.extractToken(token));
        try {
            sender.sendMail(updatedUser.getEmail(), MessageFactory.accountUpdatedMessage(), SubjectFactory.subject("Personnal informations"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return updatedUser;
    }

    @PostMapping("/update-password")
    public ResponseEntity updateUserPassword(@RequestBody PasswordDto newPassword, @RequestHeader("Authorization") String token) {
        UserDto userResult = userService.updateUserPassword(newPassword, userConfig.extractToken(token));
        if (userResult != null) {
            try {
                sender.sendMail(userResult.getEmail(), MessageFactory.passwordChangeMessage(), SubjectFactory.subject("Password"));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity((userResult != null) ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
    }
}
