package ru.otus.lesson.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.CsvParser;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionDaoImpl implements QuestionDao {
    private final CsvParser csvParser;

    @Override
    public List<ExamQuestion> getExamQuestions() {
        return csvParser.parseExamQuestions();
    }
}
