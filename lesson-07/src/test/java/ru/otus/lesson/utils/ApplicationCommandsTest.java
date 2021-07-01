package ru.otus.lesson.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.lesson.service.ShellFacadeService;

import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ApplicationCommandsTest {
    private final static String BOOK_TITLE = "b4";
    private final static String GENRE_NAME = "g4";
    private final static String AUTHOR_FULL_NAME = "a4";
    private final static String COMMAND_CREATE = "create %s %s %s";
    private final static String COMMAND_READ_ALL = "read-all";
    private final static String COMMAND_READ = "read %s";
    private final static String COMMAND_UPDATE = "update %s %s %s";
    private final static String COMMAND_DELETE = "delete %s";

    @Autowired
    Shell shell;
    @MockBean
    ShellFacadeService shellFacadeService;
    @MockBean
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShellCreate() {
        shell.evaluate(() -> String.format(COMMAND_CREATE, BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME));

        verify(shellFacadeService).addBook(BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME);
    }

    @Test
    void testShellReadAll() {
        shell.evaluate(() -> COMMAND_READ_ALL);

        verify(shellFacadeService).readAllBooks();
    }

    @Test
    void testShellRead() {
        shell.evaluate(() -> String.format(COMMAND_READ, BOOK_TITLE));

        verify(shellFacadeService).readBookByTitle(BOOK_TITLE);
    }


    @Test
    void testShellUpdate() {
        shell.evaluate(() -> String.format(COMMAND_UPDATE, BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME));

        verify(shellFacadeService).updateBook(BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME);
    }

    @Test
    void testShellDelete() {
        shell.evaluate(() -> String.format(COMMAND_DELETE, BOOK_TITLE));

        verify(shellFacadeService).deleteBook(BOOK_TITLE);
    }

}
