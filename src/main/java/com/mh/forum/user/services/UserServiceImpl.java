package com.mh.forum.user.services;

import com.mh.forum.configuration.UserConfig;
import com.mh.forum.exceptions.ForbiddenException;

import com.mh.forum.exceptions.UserAuthenticationException;
import com.mh.forum.exceptions.UserExistsException;
import com.mh.forum.user.dao.UserRepository;
import com.mh.forum.user.dto.AddUserDto;
import com.mh.forum.user.dto.UserDto;
import com.mh.forum.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConfig userConfig;
   /* @Autowired
    PasswordEncoder passwordEncoder;*/

    @Override
    public UserDto addUser(AddUserDto addUserDto) {
        //to correct
        if (userRepository.existsById(addUserDto.getEmail())){
            throw new UserExistsException();
        }
       // String hashPasswd = passwordEncoder.encode(addUserDto.getPassword());
        User user = User.builder()
                .email(addUserDto.getEmail())
                .password(addUserDto.getPassword())
                .firstName(addUserDto.getFirstName())
                .lastName(addUserDto.getLastName())
                .build();
        userRepository.save(user);
            return userToUserDto(user);
    }

    @Override
    public UserDto login(String idUser) {

        UserAuthentication userAuthentication= userConfig.tokenDecode(idUser);
        User user = userRepository.findById(userAuthentication.getEmail())
                .orElseThrow(() -> new UserAuthenticationException());
        if(!userAuthentication.getPassword().equals(user.getPassword())){
            throw new ForbiddenException();
        }
        // User user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));;
        return userToUserDto(user);
    }

    @Override
    public UserDto deleteUser(String login) {
        return null;
    }


    private UserDto userToUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
