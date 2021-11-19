package ru.otus.lesson.utils;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.lesson.domain.Question;

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
