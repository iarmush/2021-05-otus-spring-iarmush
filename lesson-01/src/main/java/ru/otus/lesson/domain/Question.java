package ru.otus.lesson.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {

    @CsvBindByName
    private String title;
    @CsvRecurse
    private AnswerOptions answerOptions;

    @Override
    public String toString() {
        return title + "\n"
            + answerOptions;
    }
}
