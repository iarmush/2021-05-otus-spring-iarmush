package ru.otus.lesson.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

public class ExamQuestion {
    @CsvBindByName
    private String title;
    @CsvRecurse
    private AnswerOptions answerOptions;
    @CsvBindByName
    private String answer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnswerOptions getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(AnswerOptions answerOptions) {
        this.answerOptions = answerOptions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
