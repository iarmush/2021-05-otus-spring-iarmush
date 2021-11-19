package ru.otus.lesson.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.ExamQuestion;

import java.util.List;

import static org.mockito.Mockito.*;

class CsvParserImplTest {
    public static final String EXAM_TEST_CSV = "csv/exam-test.csv";
    @Mock
    ExamResource examResource;
    @InjectMocks
    CsvParserImpl csvParserImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParseExamQuestions() {
        when(examResource.createInputStream())
                .thenReturn(examResource.getClass().getClassLoader().getResourceAsStream(EXAM_TEST_CSV));

        List<ExamQuestion> result = csvParserImpl.parseExamQuestions();
        Assertions.assertEquals(5, result.size());
    }
}
