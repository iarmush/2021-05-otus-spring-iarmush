package ru.otus.lesson.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.otus.lesson.domain.ExamQuestion;

@Component
public class CsvParserImpl implements CsvParser {

    private final ExamResource examResource;

    public CsvParserImpl(ExamResource examResource) {
        this.examResource = examResource;
    }

    @Override
    public List<ExamQuestion> parseExamQuestions() {
        InputStream inputStream = examResource.createInputStream();

        return new CsvToBeanBuilder(new BufferedReader(new InputStreamReader(inputStream)))
            .withType(ExamQuestion.class)
            .build().parse();
    }
}
