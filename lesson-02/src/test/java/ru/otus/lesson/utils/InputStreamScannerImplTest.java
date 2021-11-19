package ru.otus.lesson.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

class InputStreamScannerImplTest {
    public static final String TEST = "test";
    @Spy
    InputStreamScannerImpl scannerServiceImpl = new InputStreamScannerImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAnswer() {
        when(scannerServiceImpl.getAnswer()).thenReturn(new ByteArrayInputStream(TEST.getBytes()));

        InputStream result = scannerServiceImpl.getAnswer();

        String text = new BufferedReader(new InputStreamReader(result, StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\n"));

        Assertions.assertEquals("test", text);
    }
}