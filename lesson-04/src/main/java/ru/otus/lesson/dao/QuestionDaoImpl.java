package ru.otus.lesson.dao;

import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.CsvParser;

import java.util.List;

@Service
public class QuestionDaoImpl implements QuestionDao {
    private final CsvParser csvParser;

    public QuestionDaoImpl(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    @Override
    public List<ExamQuestion> getExamQuestions() {
        return csvParser.parseExamQuestions();
    }
}
