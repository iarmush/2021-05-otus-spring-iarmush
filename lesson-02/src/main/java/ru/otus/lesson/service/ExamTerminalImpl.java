package ru.otus.lesson.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.QuestionDao;
import ru.otus.lesson.domain.ExamQuestion;

@Service
public class ExamTerminalImpl implements ExamTerminal {

    private final QuestionDao questionDao;
    private final ExamService examService;

    public ExamTerminalImpl(QuestionDao questionDao, ExamService examService) {
        this.questionDao = questionDao;
        this.examService = examService;
    }

    @Override
    public void processExam() {
        List<ExamQuestion> examQuestions = questionDao.getExamQuestions();
        for (ExamQuestion examQuestion : examQuestions) {
            examService.printQuestion(examQuestion);
            examService.checkAnswer(examQuestion);
        }
        examService.printResult();
    }
}
