package ru.otus.lesson.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.lesson.config.ExamConfig;
import ru.otus.lesson.domain.AnswerOptions;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

import java.util.Locale;

import static org.mockito.Mockito.*;

@SpringBootTest
class MessageSourceExamImplTest {
    private static final String USER = "User";
    public static final String QUESTION_1 = "Question 1";

    @Autowired
    MessageSourceExamImpl messageSourceExamImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrintExamQuestion() {
        AnswerOptions answerOptions = new AnswerOptions();
        answerOptions.setOptionA("A1");
        answerOptions.setOptionB("B1");
        answerOptions.setOptionC("С1");
        answerOptions.setOptionD("D1");

        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setTitle(QUESTION_1);
        examQuestion.setAnswerOptions(answerOptions);

        messageSourceExamImpl.printExamQuestion(examQuestion);
    }

    @Test
    void testPrintExamResult() {
        messageSourceExamImpl.printExamResult(USER, new User());
    }

    @Test
    void testPrintEnterUserName() {
        messageSourceExamImpl.printEnterUserName();
    }

    @Test
    void testPrintEnterUserSurname() {
        messageSourceExamImpl.printEnterUserSurname();
    }
}
