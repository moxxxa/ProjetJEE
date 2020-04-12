package com.mh.forum;

import com.mh.forum.dao.CategoryRepository;
import com.mh.forum.entity.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }


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
