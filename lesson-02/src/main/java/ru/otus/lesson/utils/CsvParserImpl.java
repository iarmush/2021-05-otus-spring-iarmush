package ru.otus.lesson.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.ExamQuestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

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
