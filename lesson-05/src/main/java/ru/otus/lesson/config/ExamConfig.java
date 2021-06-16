package ru.otus.lesson.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "exam")
@Component
public class ExamConfig {
    private String csvPath;
    private Integer minimalScore;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public Integer getMinimalScore() {
        return minimalScore;
    }

    public void setMinimalScore(Integer minimalScore) {
        this.minimalScore = minimalScore;
    }
}
