package ru.otus.lesson.service;

import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

public interface ExamService {
    void getQuestion(ExamQuestion examQuestion);

    void checkAnswer(ExamQuestion examQuestion);

    void getResult(User user);
}
