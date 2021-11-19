package ru.otus.lesson.utils;

import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.ExamQuestion;

class CsvParserImplTest {

    public static final String EXAM_TEST_CSV = "exam-test.csv";
    @Mock
    InputStreamResource inputStreamResource;
    @InjectMocks
    CsvParserImpl csvParserImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParseExamQuestions() {
        when(inputStreamResource.createInputStream())
            .thenReturn(inputStreamResource.getClass().getClassLoader().getResourceAsStream(EXAM_TEST_CSV));

        List<ExamQuestion> result = csvParserImpl.parseExamQuestions();
        Assertions.assertEquals(5, result.size());
    }
}
