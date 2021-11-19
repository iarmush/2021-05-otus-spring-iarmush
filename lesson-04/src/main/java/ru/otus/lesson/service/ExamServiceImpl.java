package ru.otus.lesson.service;

import java.util.Scanner;
import org.springframework.stereotype.Service;
import ru.otus.lesson.config.ExamConfig;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;
import ru.otus.lesson.utils.MessageSourceExam;
import ru.otus.lesson.utils.OutputScanner;

@Service
public class ExamServiceImpl implements ExamService {

    private static final String EXAM_PASSED = "Exam passed";
    private static final String EXAM_FAILED = "Exam failed";
    private Integer currentScore = 0;

    private final Integer minimalScore;
    private final OutputScanner outputScanner;
    private final MessageSourceExam messageSourceExam;

    public ExamServiceImpl(ExamConfig examConfig, OutputScanner outputScanner, MessageSourceExam messageSourceExam) {
        this.minimalScore = examConfig.getMinimalScore();
        this.outputScanner = outputScanner;
        this.messageSourceExam = messageSourceExam;
    }

    @Override
    public void getQuestion(ExamQuestion examQuestion) {
        messageSourceExam.printExamQuestion(examQuestion);
    }

    @Override
    public void checkAnswer(ExamQuestion examQuestion) {
        Scanner scanner = new Scanner(outputScanner.getConsoleOutput());
        String currentAnswer = scanner.nextLine();

        if (currentAnswer.equals(examQuestion.getAnswer())) {
            currentScore = currentScore + 1;
        }
    }

    @Override
    public void getResult(User user) {
        if (currentScore >= minimalScore) {
            messageSourceExam.printExamResult(EXAM_PASSED, user);
        } else {
            messageSourceExam.printExamResult(EXAM_FAILED, user);
        }
    }
}
