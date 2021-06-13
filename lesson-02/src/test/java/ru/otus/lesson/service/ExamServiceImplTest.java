package ru.otus.lesson.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.InputStreamScanner;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.when;

class ExamServiceImplTest {
    public static final String TEST = "test";
    @Mock
    InputStreamScanner inputStreamScanner;
    @InjectMocks
    ExamServiceImpl examServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(examServiceImp, "minimalScore", 1);
    }

    @Test
    void testPrintQuestion() {
        examServiceImp.printQuestion(new ExamQuestion());
    }

    @Test
    void testCheckAnswer() {
        when(inputStreamScanner.getAnswer()).thenReturn(new ByteArrayInputStream(TEST.getBytes()));

        examServiceImp.checkAnswer(new ExamQuestion());
    }

    @Test
    void testPrintResult() {
        examServiceImp.printResult();
    }
}
