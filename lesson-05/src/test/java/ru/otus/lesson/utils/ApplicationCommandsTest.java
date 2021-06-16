package ru.otus.lesson.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.lesson.service.ExamService;
import ru.otus.lesson.service.ExamTerminal;
import ru.otus.lesson.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationCommandsTest {
    @Autowired
    Shell shell;
    @Autowired
    ExamTerminal examTerminal;
    @MockBean
    UserService userService;
    @MockBean
    ExamService examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userService, examService);
    }

    @Test
    void testProcessExam() {
        shell.evaluate(() -> "start");

        verify(userService).enterUserName();
        verify(userService).enterUserSurname();
        verify(userService).getUser();
        verify(examService).getResult(any());
        verify(examService, times(5)).getQuestion(any());
        verify(examService, times(5)).checkAnswer(any());
    }
}
