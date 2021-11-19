package ru.otus.lesson.utils;

import java.util.List;
import ru.otus.lesson.domain.Question;

public interface CsvParser {

    List<Question> parseQuestions();

}
