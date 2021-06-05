package ru.otus.lesson.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class InputStreamResourceImplTest {
    public static final String EXAM_TEST_CSV = "exam-test.csv";

    InputStreamResourceImpl inputStreamResourceImpl = new InputStreamResourceImpl(EXAM_TEST_CSV);

    @Test
    void testCreateInputStream() {
        InputStream result = inputStreamResourceImpl.createInputStream();
        Assertions.assertNotNull(result);
    }
}
