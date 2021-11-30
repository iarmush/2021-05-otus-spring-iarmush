package ru.otus.lesson.utils;

import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputStreamResourceImplTest {

    public static final String EXAM_TEST_CSV = "exam-test.csv";

    InputStreamResourceImpl inputStreamResourceImpl = new InputStreamResourceImpl(EXAM_TEST_CSV);

    @Test
    void testCreateInputStream() {
        InputStream result = inputStreamResourceImpl.createInputStream();
        Assertions.assertNotNull(result);
    }
}
