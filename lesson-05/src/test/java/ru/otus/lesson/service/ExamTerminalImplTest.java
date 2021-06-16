package ru.otus.lesson.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.lesson.dao.QuestionDao;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class ExamTerminalImplTest {
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

    @Test
    void testProcessExam() {
        examTerminal.processExam();
    }
}
