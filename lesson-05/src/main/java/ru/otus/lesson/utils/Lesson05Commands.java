package ru.otus.lesson.utils;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lesson.service.ExamTerminal;

@ShellComponent
public class Lesson05Commands {

    private final ExamTerminal examTerminal;

    public Lesson05Commands(ExamTerminal examTerminal) {
        this.examTerminal = examTerminal;
    }

    @ShellMethod(value = "Start exam", key = {"s", "start", "start exam"})
    public void processExam() {
        examTerminal.processExam();
    }
}
