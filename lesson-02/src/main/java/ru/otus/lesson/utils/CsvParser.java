package ru.otus.lesson.utils;

import java.util.List;
import ru.otus.lesson.domain.ExamQuestion;

public interface CsvParser {

    List<ExamQuestion> parseExamQuestions();

}
