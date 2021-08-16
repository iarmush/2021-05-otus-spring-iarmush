package ru.otus.lesson.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.Book;

import static org.mockito.Mockito.*;

class LibraryServiceImplTest {
    public static final String TITLE = "title";
    public static final String FULL_NAME = "fullName";
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String NEW_TEXT = "new text";
    public static final Book BOOK = new Book();

    @Mock
    BookService bookService;
    @InjectMocks
    LibraryServiceImpl libraryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(bookService);
    }

    @Test
    void testAddBook() {
        libraryServiceImpl.addBook(TITLE, FULL_NAME, NAME);

        verify(bookService).create(any(Book.class));
    }

    @Test
    void testReadAllBooks() {
        libraryServiceImpl.readAllBooks();

        verify(bookService).selectAll();
    }

    @Test
    void testReadBookByTitle() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.readBookByTitle(TITLE);

        verify(bookService).selectByTitle(TITLE);
    }

    @Test
    void testUpdateBook() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.updateBook(TITLE, FULL_NAME, NAME);

        verify(bookService).selectByTitle(TITLE);
        verify(bookService).update(BOOK);
    }

    @Test
    void testDeleteBook() {
        libraryServiceImpl.deleteBook(TITLE);

        verify(bookService).delete(TITLE);
    }

    @Test
    void testCreateComment() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.createComment(TEXT, TITLE);

        verify(bookService).selectByTitle(TITLE);
        verify(bookService).update(BOOK);
    }

    @Test
    public void readCommentByBookTitle() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.readCommentByBookTitle(TITLE);

        verify(bookService).selectByTitle(TITLE);
    }

    @Test
    void testUpdateCommentByBookTitle() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.updateCommentByBookTitle(TITLE, TEXT, NEW_TEXT);

        verify(bookService).selectByTitle(TITLE);
        verify(bookService).update(BOOK);
    }

    @Test
    public void testDeleteCommentByBookTitle() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.deleteCommentByBookTitle(TITLE, TEXT);

        verify(bookService).selectByTitle(TITLE);
        verify(bookService).update(BOOK);
    }

    @Test
    public void testDeleteAllCommentByBookTitle() {
        when(bookService.selectByTitle(TITLE)).thenReturn(BOOK);

        libraryServiceImpl.deleteAllCommentsByBookTitle(TITLE);

        verify(bookService).selectByTitle(TITLE);
        verify(bookService).update(BOOK);
    }
}
