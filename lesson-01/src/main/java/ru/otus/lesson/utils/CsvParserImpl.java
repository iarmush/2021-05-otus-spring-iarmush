package ru.otus.lesson.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.*;
import ru.otus.lesson.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@AllArgsConstructor
public class CsvParserImpl implements CsvParser {
    private final String csvPath;

    @Override
    public List<Question> parseQuestions() {
        InputStream inputStream = createInputStream();

        return new CsvToBeanBuilder(new BufferedReader(new InputStreamReader((inputStream))))
                .withType(Question.class)
                .build()
                .parse();
    }

    private InputStream createInputStream() {
        return CsvParserImpl.class.getClassLoader().getResourceAsStream(csvPath);
    }
}
