package ru.otus.lesson.dao;

import lombok.AllArgsConstructor;
import ru.otus.lesson.domain.Question;
import ru.otus.lesson.utils.CsvParser;

import java.util.List;

@AllArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final CsvParser csvParser;

    @Override
    public StringBuilder prepareQuestionsForPrint() {
        List<Question> questionList = csvParser.parseQuestions();
        StringBuilder stringBuilder = new StringBuilder();
        questionList.forEach(stringBuilder::append);

        return stringBuilder;
    }
}
