package ru.otus.lesson.utils;

import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

public interface MessageSourceExam {

    void printExamQuestion(ExamQuestion examQuestion);

    void printExamResult(String result, User user);

    void printEnterUserName();

    void printEnterUserSurname();
}
