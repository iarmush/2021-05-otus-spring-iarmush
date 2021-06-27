package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.CsvParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

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
        ExamQuestion examQuestion = new ExamQuestion();
        List<ExamQuestion> examQuestionList = Collections.singletonList(examQuestion);

        when(csvParser.parseExamQuestions()).thenReturn(examQuestionList);

        List<ExamQuestion> result = questionDaoImpl.getExamQuestions();
        Assertions.assertEquals(examQuestionList, result);
    }
}
