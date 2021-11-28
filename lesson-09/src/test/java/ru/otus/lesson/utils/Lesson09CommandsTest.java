package ru.otus.lesson.utils;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.lesson.service.LibraryService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class Lesson09CommandsTest {

    private final static String BOOK_TITLE = "b4";
    private final static String GENRE_NAME = "g4";
    private final static String AUTHOR_FULL_NAME = "a4";
    private final static String COMMENT_TEXT = "c4";
    private final static String COMMENT_NEW_TEXT = "c4";

    private final static String COMMAND_CREATE_BOOK = "create-book %s %s %s";
    private final static String COMMAND_READ_ALL_BOOKS = "read-all-books";
    private final static String COMMAND_READ_BOOK = "read-book %s";
    private final static String COMMAND_UPDATE_BOOK = "update-book %s %s %s";
    private final static String COMMAND_DELETE_BOOK = "delete-book %s";

    private final static String COMMAND_CREATE_COMMENT = "create-comment %s %s";
    private final static String COMMAND_READ_ALL_COMMENTS = "read-all-comments";
    private final static String COMMAND_READ_COMMENT = "read-comment %s";
    private final static String COMMAND_READ_COMMENT_BY_BOOK_TITLE = "read-comment-by-book-title %s";
    private final static String COMMAND_UPDATE_COMMENT = "update-comment %s %s";
    private final static String COMMAND_DELETE_COMMENT = "delete-comment %s";

    @Autowired
    Shell shell;
    @MockBean
    LibraryService libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShellCreateBook() {
        shell.evaluate(() -> String.format(COMMAND_CREATE_BOOK, BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME));

        verify(libraryService).addBook(BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME);
    }

    @Test
    void testShellReadAllBooks() {
        shell.evaluate(() -> COMMAND_READ_ALL_BOOKS);

        verify(libraryService).readAllBooks();
    }

    @Test
    void testShellReadBook() {
        shell.evaluate(() -> String.format(COMMAND_READ_BOOK, BOOK_TITLE));

        verify(libraryService).readBookByTitle(BOOK_TITLE);
    }


    @Test
    void testShellUpdateBook() {
        shell.evaluate(() -> String.format(COMMAND_UPDATE_BOOK, BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME));

        verify(libraryService).updateBook(BOOK_TITLE, AUTHOR_FULL_NAME, GENRE_NAME);
    }

    @Test
    void testShellDeleteBook() {
        shell.evaluate(() -> String.format(COMMAND_DELETE_BOOK, BOOK_TITLE));

        verify(libraryService).deleteBookByTitle(BOOK_TITLE);
    }

    @Test
    void testShellCreateComment() {
        shell.evaluate(() -> String.format(COMMAND_CREATE_COMMENT, COMMENT_TEXT, BOOK_TITLE));

        verify(libraryService).createComment(COMMENT_TEXT, BOOK_TITLE);
    }

    @Test
    void testShellReadAllComments() {
        shell.evaluate(() -> COMMAND_READ_ALL_COMMENTS);

        verify(libraryService).readAllComments();
    }

    @Test
    void testShellReadComment() {
        shell.evaluate(() -> String.format(COMMAND_READ_COMMENT, COMMENT_TEXT));

        verify(libraryService).readComment(COMMENT_TEXT);
    }

    @Test
    void testShellReadCommentByBookTitle() {
        shell.evaluate(() -> String.format(COMMAND_READ_COMMENT_BY_BOOK_TITLE, BOOK_TITLE));

        verify(libraryService).readCommentByBookTitle(BOOK_TITLE);
    }

    @Test
    void testShellUpdateComment() {
        shell.evaluate(() -> String.format(COMMAND_UPDATE_COMMENT, COMMENT_TEXT, COMMENT_NEW_TEXT));

        verify(libraryService).updateComment(COMMENT_TEXT, COMMENT_NEW_TEXT);
    }

    @Test
    void testShellDeleteComment() {
        shell.evaluate(() -> String.format(COMMAND_DELETE_COMMENT, COMMENT_TEXT));

        verify(libraryService).deleteCommentByText(COMMENT_TEXT);
    }
}
