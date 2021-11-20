package ru.otus.lesson.utils;

import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ExamResourceImplTest {

    @Autowired
    ExamResourceImpl examResourceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateInputStream() {
        InputStream result = examResourceImpl.createInputStream();
        Assertions.assertNotNull(result);
    }
}
