package ru.otus.lesson.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.lesson.dao.QuestionDao;

@ExtendWith(MockitoExtension.class)
class QuestionPrinterImplTest {

    @Mock
    QuestionDao questionDao;
    @InjectMocks
    QuestionPrinterImpl questionPrinterImpl;

    @Test
    void testPrintQuestions() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("question");

        when(questionDao.prepareQuestionsForPrint()).thenReturn(stringBuilder);

        questionPrinterImpl.printQuestions();
    }
}
