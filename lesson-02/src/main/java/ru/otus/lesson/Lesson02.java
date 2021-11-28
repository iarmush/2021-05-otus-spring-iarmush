package ru.otus.lesson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.lesson.service.ExamTerminal;

@ComponentScan
public class Lesson02 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Lesson02.class);

        ExamTerminal questionPrinter = annotationConfigApplicationContext.getBean(ExamTerminal.class);
        questionPrinter.processExam();
    }
}
