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
    @Mock
    BookService bookService;
    @Mock
    GenreService genreService;
    @Mock
    AuthorService authorService;
    @Mock
    CommentService commentService;
    @InjectMocks
    LibraryServiceImpl libraryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(bookService, genreService, authorService, commentService);
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
        when(bookService.selectByTitle(TITLE)).thenReturn(new Book());

        libraryServiceImpl.readBookByTitle(TITLE);

        verify(bookService).selectByTitle(TITLE);
    }

    @Test
    void testUpdateBook() {
        when(bookService.selectByTitle(TITLE)).thenReturn(new Book());

        libraryServiceImpl.updateBook(TITLE, FULL_NAME, NAME);

        verify(bookService).selectByTitle(any());
        verify(genreService).create(NAME);
        verify(genreService).selectByName(NAME);
        verify(authorService).create(FULL_NAME);
        verify(authorService).selectByName(FULL_NAME);
        verify(bookService).update(any());
    }

    @Test
    void testDeleteBook() {
        libraryServiceImpl.deleteBookByTitle(TITLE);

        verify(bookService).deleteByTitle(TITLE);
    }

    @Test
    void createComment() {
        libraryServiceImpl.createComment(TEXT, NEW_TEXT);

        verify(bookService).selectByTitle(any());
        verify(commentService).createComment(any());
    }

    @Test
    void readAllComments() {
        libraryServiceImpl.readAllComments();

        verify(commentService).selectAll();
    }

    @Test
    void readComment() {
        libraryServiceImpl.readComment(TEXT);

        verify(commentService).selectByText(TEXT);
    }

    @Test
    void updateComment() {
        libraryServiceImpl.updateComment(TEXT, NEW_TEXT);

        verify(commentService).update(TEXT, NEW_TEXT);
    }

    @Test
    public void deleteComment() {
        libraryServiceImpl.deleteCommentByText(TEXT);

        verify(commentService).deleteByText(TEXT);
    }

    @Test
    public void readCommentByBookTitle() {
        when(bookService.selectByTitle(TEXT)).thenReturn(new Book());

        libraryServiceImpl.readCommentByBookTitle(TEXT);

        verify(bookService).selectByTitle(TEXT);
    }
}
