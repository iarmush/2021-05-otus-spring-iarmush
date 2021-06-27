package ru.otus.lesson.domain;

import com.opencsv.bean.CsvBindByName;

public class AnswerOptions {
    @CsvBindByName(column = "option A")
    private String optionA;
    @CsvBindByName(column = "option B")
    private String optionB;
    @CsvBindByName(column = "option C")
    private String optionC;
    @CsvBindByName(column = "option D")
    private String optionD;

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}
