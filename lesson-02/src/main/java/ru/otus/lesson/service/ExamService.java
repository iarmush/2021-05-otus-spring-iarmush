package ru.otus.lesson.service;

import ru.otus.lesson.domain.ExamQuestion;

public interface ExamService {

    void printQuestion(ExamQuestion examQuestion);

    void checkAnswer(ExamQuestion examQuestion);

    void printResult();

}
