package ru.otus.lesson.dao;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.CsvParser;

class QuestionDaoImplTest {

    @Mock
    CsvParser csvParser;
    @InjectMocks
    QuestionDaoImpl questionDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExamQuestions() {
        ExamQuestion examQuestion1 = new ExamQuestion();
        ExamQuestion examQuestion2 = new ExamQuestion();

        when(csvParser.parseExamQuestions()).thenReturn(Arrays.asList(examQuestion1, examQuestion2));

        List<ExamQuestion> result = questionDaoImpl.getExamQuestions();
        Assertions.assertEquals(2, result.size());
    }
}
