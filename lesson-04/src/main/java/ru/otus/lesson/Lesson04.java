package ru.otus.lesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.lesson.service.ExamTerminalImpl;

@SpringBootApplication
public class Lesson04 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Lesson04.class, args);

        ExamTerminalImpl examTerminal = context.getBean(ExamTerminalImpl.class);
        examTerminal.processExam();
    }
}
