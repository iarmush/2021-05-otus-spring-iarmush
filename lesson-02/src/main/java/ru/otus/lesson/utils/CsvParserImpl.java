package ru.otus.lesson.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.otus.lesson.domain.ExamQuestion;

@Component
public class CsvParserImpl implements CsvParser {

    private final InputStreamResource inputStreamResource;

    public CsvParserImpl(InputStreamResource inputStreamResource) {
        this.inputStreamResource = inputStreamResource;
    }

    @Override
    public List<ExamQuestion> parseExamQuestions() {

        return new CsvToBeanBuilder(new BufferedReader(new InputStreamReader((inputStreamResource.createInputStream()))))
            .withType(ExamQuestion.class)
            .build().parse();
    }
}
