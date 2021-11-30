package ru.otus.lesson;

import com.github.cloudyrock.spring.v5.EnableMongock;
import java.sql.SQLException;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
public class Lesson26 {

    public static void main(String[] args) throws SQLException {
        Console.main(args);
        SpringApplication.run(Lesson26.class, args);
    }
}
