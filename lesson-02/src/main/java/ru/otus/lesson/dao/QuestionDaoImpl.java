package ru.otus.lesson.dao;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.CsvParser;

@AllArgsConstructor
@Service
public class QuestionDaoImpl implements QuestionDao {

    private final CsvParser csvParser;

    @Override
    public List<ExamQuestion> getExamQuestions() {
        return csvParser.parseExamQuestions();
    }
}
