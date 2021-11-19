package ru.otus.lesson.service;

import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.utils.MessageSourceExam;
import ru.otus.lesson.utils.OutputScanner;

class UserServiceImplTest {

    private final static String TEST = "test";
    @Mock
    OutputScanner outputScanner;
    @Mock
    MessageSourceExam messageSourceExam;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnterUserName() {
        when(outputScanner.getConsoleOutput()).thenReturn(new ByteArrayInputStream(TEST.getBytes()));

        userServiceImpl.enterUserName();
    }

    @Test
    void testEnterUserSurname() {
        when(outputScanner.getConsoleOutput()).thenReturn(new ByteArrayInputStream(TEST.getBytes()));

        userServiceImpl.enterUserSurname();
    }
}
