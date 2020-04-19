package com.mh.forum.services;
import com.mh.forum.configuration.UserConfig;
import com.mh.forum.dao.UserRepository;
import com.mh.forum.dto.AddUserDto;
import com.mh.forum.dto.UserDto;
import com.mh.forum.entity.User;
import com.mh.forum.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

        UserAuthentication userAuthentication=
                userConfig.tokenDecode(idUser);
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
