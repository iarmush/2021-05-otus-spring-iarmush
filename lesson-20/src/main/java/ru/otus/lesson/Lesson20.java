package ru.otus.lesson;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableReactiveMongoRepositories
public class Lesson20 {

    public static void main(String[] args) {
        SpringApplication.run(Lesson20.class, args);
    }
}
