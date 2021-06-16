package ru.otus.lesson.dao;

import ru.otus.lesson.domain.ExamQuestion;

import java.util.List;

public interface QuestionDao {
    List<ExamQuestion> getExamQuestions();
}
