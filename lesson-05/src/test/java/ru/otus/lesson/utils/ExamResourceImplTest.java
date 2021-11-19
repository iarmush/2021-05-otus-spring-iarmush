package ru.otus.lesson.utils;

import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.lesson.config.ExamConfig;

@SpringBootTest
class ExamResourceImplTest {

    @Autowired
    ExamResourceImpl examResourceImpl;

    @Configuration
    static class NestedConfiguration {

        @Bean
        ExamResourceImpl examResource(ExamConfig examConfig) {
            return new ExamResourceImpl(examConfig);
        }

        @Bean
        ExamConfig examConfig() {
            ExamConfig examConfig = new ExamConfig();
            examConfig.setCsvPath("csv/exam-test.csv");
            return examConfig;
        }
    }

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
