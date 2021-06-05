package ru.otus.lesson.utils;

import ru.otus.lesson.domain.Question;

import java.util.List;

public interface CsvParser {

    List<Question> parseQuestions();

}
