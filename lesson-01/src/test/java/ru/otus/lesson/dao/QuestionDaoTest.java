package ru.otus.lesson.dao;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.lesson.domain.AnswerOptions;
import ru.otus.lesson.domain.Question;
import ru.otus.lesson.utils.CsvParser;

@ExtendWith(MockitoExtension.class)
class QuestionDaoTest {

    @Mock
    private CsvParser csvParser;

    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoImpl(csvParser);
    }

    @Test
    void testPrepareQuestionsForPrint() {
        AnswerOptions answerOptions = new AnswerOptions();
        answerOptions.setOptionA("1");
        answerOptions.setOptionB("2");
        answerOptions.setOptionC("3");
        answerOptions.setOptionD("4");
        Question question = new Question();
        question.setTitle("tittle");
        question.setAnswerOptions(answerOptions);

        List<Question> questionList = Collections.singletonList(question);

        when(csvParser.parseQuestions()).thenReturn(questionList);

        StringBuilder stringBuilder = questionDao.prepareQuestionsForPrint();
        Assertions.assertNotNull(stringBuilder);

        verify(csvParser).parseQuestions();
    }

}
