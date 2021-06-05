package ru.otus.lesson.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lesson.domain.Question;

import java.util.List;

class CsvParserTest {
    private CsvParser csvParser;

    @BeforeEach
    void setUp() {
        csvParser = new CsvParserImpl("questions-test.csv");
    }

    @Test
    void testParseQuestionTable() {
        List<Question> result = csvParser.parseQuestions();
        Assertions.assertEquals(result.size(), 2);
    }
}
