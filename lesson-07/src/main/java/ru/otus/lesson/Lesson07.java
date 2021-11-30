package ru.otus.lesson;

import java.sql.SQLException;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson07 {

    public static void main(String[] args) throws SQLException {
        Console.main(args);
        SpringApplication.run(Lesson07.class, args);
    }
}
