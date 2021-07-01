package ru.otus.lesson.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.Book;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

class ShellFacadeServiceImplTest {
    public static final String TITLE = "title";
    public static final String FULL_NAME = "fullName";
    public static final String NAME = "name";
    @Mock
    BookService bookService;
    @Mock
    GenreService genreService;
    @Mock
    AuthorService authorService;
    @InjectMocks
    ShellFacadeServiceImpl shellFacadeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        shellFacadeServiceImpl.addBook(TITLE, FULL_NAME, NAME);

        verify(genreService).addGenre(NAME);
        verify(genreService).getByName(NAME);
        verify(authorService).addAuthor(FULL_NAME);
        verify(authorService).getByName(FULL_NAME);
        verify(bookService).insert(any(Book.class));
    }

    @Test
    void testReadAllBooks() {
        shellFacadeServiceImpl.readAllBooks();

        verify(bookService).selectAll();
    }

    @Test
    void testReadBookByTitle() {
        shellFacadeServiceImpl.readBookByTitle(TITLE);

        verify(bookService).selectByTitle(TITLE);
    }

    @Test
    void testUpdateBook() {
        shellFacadeServiceImpl.updateBook(TITLE, FULL_NAME, NAME);

        verify(genreService).addGenre(NAME);
        verify(genreService).getByName(NAME);
        verify(authorService).addAuthor(FULL_NAME);
        verify(authorService).getByName(FULL_NAME);
    }

    @Test
    void testDeleteBook() {
        shellFacadeServiceImpl.deleteBook(TITLE);

        verify(bookService).delete(TITLE);
    }
}
