package ru.otus.lesson.service;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.utils.InputStreamScanner;

@Service
public class ExamServiceImpl implements ExamService {

    private Integer currentScore = 0;

    @Value("${exam.minimal.score}")
    private Integer minimalScore;
    private final InputStreamScanner inputStreamScanner;

    public ExamServiceImpl(InputStreamScanner inputStreamScanner) {
        this.inputStreamScanner = inputStreamScanner;
    }

    @Override
    public void printQuestion(ExamQuestion examQuestion) {
        System.out.println(examQuestion.toString());
    }

    @Override
    public void checkAnswer(ExamQuestion examQuestion) {
        Scanner scanner = new Scanner(inputStreamScanner.getAnswer());
        String currentAnswer = scanner.nextLine();

        if (currentAnswer.equals(examQuestion.getAnswer())) {
            currentScore = currentScore + 1;
        }
    }

    @Override
    public void printResult() {
        if (currentScore >= minimalScore) {
            System.out.println("Exam PASSED");
        } else {
            System.out.println("Exam FAILED");
        }
    }
}
