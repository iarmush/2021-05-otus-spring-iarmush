package ru.otus.lesson.dao;

import java.util.List;
import ru.otus.lesson.domain.ExamQuestion;

public interface QuestionDao {

    List<ExamQuestion> getExamQuestions();
}
