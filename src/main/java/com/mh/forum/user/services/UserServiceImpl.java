package com.mh.forum.user.services;

import com.mh.forum.Hash.Md5Hash;
import com.mh.forum.configuration.UserConfig;
import com.mh.forum.exceptions.ForbiddenException;

import com.mh.forum.exceptions.UserAuthenticationException;
import com.mh.forum.exceptions.UserExistsException;
import com.mh.forum.exceptions.UserNotFoundException;
import com.mh.forum.user.dao.UserRepository;
import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.PasswordDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConfig userConfig;
   /* @Autowired
    PasswordEncoder passwordEncoder;*/

    @Override
    public UserDto addUser(AddUserDto addUserDto) throws NoSuchAlgorithmException {
        //to correct
        if (userRepository.existsById(addUserDto.getEmail())){
            throw new UserExistsException();
        }
        String token = UUID.randomUUID().toString() + Md5Hash.hashThis(addUserDto.getPassword());
        User user = User.builder()
                .email(addUserDto.getEmail())
                .password(Md5Hash.hashThis(addUserDto.getPassword()))
                .firstName(addUserDto.getFirstName())
                .lastName(addUserDto.getLastName())
                .token(token)
                .build();
        userRepository.save(user);
        return userToUserDto(user);
    }

    public UserDto login(String token) throws NoSuchAlgorithmException {

        UserAuthentication userAuthentication = userConfig.tokenDecode(token);

        //User user = userRepository.findById(userAuthentication.getEmail()).orElseThrow(() -> new UserAuthenticationException());
        User user = userRepository.findUserByEmail(userAuthentication.getEmail());
        if (null == user) {
            throw new UserAuthenticationException();
        }

        if (!Md5Hash.hashThis(userAuthentication.getPassword()).equals(user.getPassword())) {
            throw new ForbiddenException();
        }
        return userToUserDto(user);
    }

    public UserDto updateUser(UserDto userChange, String token) {
        User user = userRepository.findUserByToken(token);
        if (null == user) {
            throw new UserNotFoundException();
        }
        user.setEmail(userChange.getEmail());
        user.setFirstName(userChange.getFirstName());
        user.setLastName(userChange.getLastName());
        userRepository.save(user);
        return userToUserDto(user);
    }

    @Override
    public UserDto deleteUser(String login) {
        return null;
    }


    private UserDto userToUserDto(User user) {
        return UserDto.builder()
                .idUser(user.getIdUser())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .token(user.getToken())
                .build();
    }

    public UserDto updateUserPassword(PasswordDto newPassword, String token) {
        User user = userRepository.findUserByToken(token);
        if (null == user) {
            return null;
        }
        user.setPassword(newPassword.getPassword());
        userRepository.save(user);
        return userToUserDto(user);
    }

    public UserDto findUserByToken(String token) {
        User user = userRepository.findUserByToken(token);
        return userToUserDto(user);
    }
}
