package ru.otus.lesson.utils;

import java.io.InputStream;
import java.util.Objects;
import org.springframework.stereotype.Component;
import ru.otus.lesson.config.ExamConfig;

@Component
public class ExamResourceImpl implements ExamResource {

    public static final String FILE_NOT_FOUND = "File not found";
    private final String csvPath;

    public ExamResourceImpl(ExamConfig examConfig) {
        this.csvPath = examConfig.getCsvPath();
    }

    @Override
    public InputStream createInputStream() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(csvPath);

        Objects.requireNonNull(resourceAsStream, () -> {
            throw new NullPointerException(FILE_NOT_FOUND);
        });

        return resourceAsStream;
    }
}
