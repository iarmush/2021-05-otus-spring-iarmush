package ru.otus.lesson.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class InputStreamResourceImpl implements InputStreamResource {
    private final String csvPath;

    public InputStreamResourceImpl(@Value("${exam.csv.path}") String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public InputStream createInputStream() {
        return getClass().getClassLoader().getResourceAsStream(csvPath);
    }
}
