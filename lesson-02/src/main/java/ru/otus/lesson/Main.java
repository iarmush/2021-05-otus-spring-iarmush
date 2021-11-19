package ru.otus.lesson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.lesson.service.ExamTerminal;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExamTerminal questionPrinter = annotationConfigApplicationContext.getBean(ExamTerminal.class);
        questionPrinter.processExam();
    }
}
