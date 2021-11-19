package ru.otus.lesson.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.QuestionDao;
import ru.otus.lesson.domain.ExamQuestion;

import java.util.Collections;

import static org.mockito.Mockito.*;

class ExamTerminalImplTest {
    @Mock
    QuestionDao questionDao;
    @Mock
    ExamService examService;
    @InjectMocks
    ExamTerminalImpl examTerminalImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(questionDao, examService);
    }

    @Test
    void testProcessExam() {
        ExamQuestion examQuestion = new ExamQuestion();
        when(questionDao.getExamQuestions()).thenReturn(Collections.singletonList(examQuestion));

        examTerminalImpl.processExam();

        verify(questionDao).getExamQuestions();
        verify(examService).printQuestion(examQuestion);
        verify(examService).checkAnswer(examQuestion);
        verify(examService).printResult();
    }
}
