package ru.otus.lesson.service;

import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.config.ExamConfig;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;
import ru.otus.lesson.utils.MessageSourceExam;
import ru.otus.lesson.utils.OutputScanner;

class ExamServiceImplTest {

    private final static String TEST = "test";
    @Mock
    OutputScanner outputScanner;
    @Mock
    MessageSourceExam messageSourceExam;
    @Mock
    ExamConfig examConfig;
    @InjectMocks
    ExamServiceImpl examServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestion() {
        examServiceImpl.getQuestion(new ExamQuestion());
    }

    @Test
    void testCheckAnswer() {
        when(outputScanner.getConsoleOutput()).thenReturn(new ByteArrayInputStream(TEST.getBytes()));

        examServiceImpl.checkAnswer(new ExamQuestion());
    }

    @Test
    void testGetResult() {
        examServiceImpl.getResult(new User());
    }
}
