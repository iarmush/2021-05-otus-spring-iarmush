package ru.otus.lesson;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
@EnableCircuitBreaker
public class Lesson35Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson35Application.class, args);
    }
}
