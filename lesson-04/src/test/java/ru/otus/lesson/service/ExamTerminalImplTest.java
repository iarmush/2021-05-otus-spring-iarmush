package ru.otus.lesson.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.QuestionDao;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

class ExamTerminalImplTest {
    @Mock
    QuestionDao questionDao;
    @Mock
    ExamService examService;
    @Mock
    UserService userService;
    @InjectMocks
    ExamTerminalImpl examTerminalImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessExam() {
        ExamQuestion examQuestion = new ExamQuestion();
        List<ExamQuestion> examQuestionList = Collections.singletonList(examQuestion);

        when(questionDao.getExamQuestions()).thenReturn(examQuestionList);
        when(userService.getUser()).thenReturn(new User());

        examTerminalImpl.processExam();
    }
}
