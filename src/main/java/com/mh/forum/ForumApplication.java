package com.mh.forum;

import com.mh.forum.dao.CategoryRepository;
import com.mh.forum.dao.UserRepository;
import com.mh.forum.entity.Category;

import com.mh.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ForumApplication{

    @Autowired
    UserRepository userRepository;

   /* @Autowired
    PasswordEncoder passwordEncoder;*/

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

/*
    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsById("5e94f0996d3c33263a489edb")) {
        //if(!userRepository.existsById("5e94f0996d3c33263a489edb")) {
            String hashPassword = passwordEncoder.encode("mouna");
            User admin = User.builder()
                    .email("mouna@gmail")
                    .password(hashPassword)
                    .firstName("Mouna")
                    .lastName("HICHRI")
                    .build();
            userRepository.save(admin);
        }
    }
*/

    @Bean
    CommandLineRunner runner(CategoryRepository categoryRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                categoryRepository.deleteAll();
                categoryRepository.saveAll(
                        List.of(
                                new Category("Science"),
                                new Category("Space"),
                                new Category("Technologie")
                        )
                );

            }
        };
    }
}
