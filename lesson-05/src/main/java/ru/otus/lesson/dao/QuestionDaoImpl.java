package ru.otus.lesson.dao;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.CsvParser;

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
