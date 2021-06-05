package ru.otus.lesson.service;

import lombok.AllArgsConstructor;
import ru.otus.lesson.dao.QuestionDao;

@AllArgsConstructor
public class QuestionPrinterImpl implements QuestionPrinter {
    private final QuestionDao questionDao;

    @Override
    public void printQuestions() {
        StringBuilder stringBuilder = questionDao.prepareQuestionsForPrint();
        System.out.println(stringBuilder);
    }
}
