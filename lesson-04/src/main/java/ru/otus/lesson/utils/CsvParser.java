package ru.otus.lesson.utils;

import ru.otus.lesson.domain.ExamQuestion;

import java.util.List;

public interface CsvParser {
    List<ExamQuestion> parseExamQuestions();
}
