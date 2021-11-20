package ru.otus.lesson.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.QuestionDao;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

@Service
public class ExamTerminalImpl implements ExamTerminal {

    private final QuestionDao questionDao;
    private final ExamService examService;
    private final UserService userService;

    public ExamTerminalImpl(QuestionDao questionDao, ExamService examService, UserService userService) {
        this.questionDao = questionDao;
        this.examService = examService;
        this.userService = userService;
    }

    @Override
    public void processExam() {
        userService.enterUserName();
        userService.enterUserSurname();

        List<ExamQuestion> examQuestions = questionDao.getExamQuestions();
        for (ExamQuestion examQuestion : examQuestions) {
            examService.getQuestion(examQuestion);
            examService.checkAnswer(examQuestion);
        }

        User currentUser = userService.getUser();
        examService.getResult(currentUser);
    }
}
