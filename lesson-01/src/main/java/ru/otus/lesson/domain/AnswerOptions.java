package ru.otus.lesson.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerOptions {

    @CsvBindByName(column = "option A")
    private String optionA;
    @CsvBindByName(column = "option B")
    private String optionB;
    @CsvBindByName(column = "option C")
    private String optionC;
    @CsvBindByName(column = "option D")
    private String optionD;

    @Override
    public String toString() {
        return "Choose answer option: \n" +
            "A) " + optionA + "\n" +
            "B) " + optionB + "\n" +
            "C) " + optionC + "\n" +
            "D) " + optionD + "\n\n";
    }
}
