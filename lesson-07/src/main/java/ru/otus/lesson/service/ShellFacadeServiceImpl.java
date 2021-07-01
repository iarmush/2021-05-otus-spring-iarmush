package ru.otus.lesson.service;

import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

import java.util.List;

@Service
public class ShellFacadeServiceImpl implements ShellFacadeService {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    public ShellFacadeServiceImpl(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Override
    public void addBook(String title, String fullName, String name) {
        Author author = checkExistOrCreateAuthor(fullName);
        Genre genre = checkExistOrCreateGenre(name);
        Book book = new Book(title, author, genre);

        bookService.insert(book);
    }

    @Override
    public void readAllBooks() {
        List<Book> bookList = bookService.selectAll();
        bookList.forEach(System.out::println);
    }

    @Override
    public void readBookByTitle(String title) {
        Book book = bookService.selectByTitle(title);
        System.out.println(book);
    }

    @Override
    public void updateBook(String title, String fullName, String name) {
        Author author = checkExistOrCreateAuthor(fullName);
        Genre genre = checkExistOrCreateGenre(name);

        bookService.update(new Book(title, author, genre));
    }

    @Override
    public void deleteBook(String title) {
        bookService.delete(title);
    }

    private Author checkExistOrCreateAuthor(String fullName) {
        Author author = authorService.getByName(fullName);
        if (author == null) {
            long authorId = authorService.addAuthor(fullName);
            author = new Author(authorId, fullName);
        }
        return author;
    }

    private Genre checkExistOrCreateGenre(String name) {
        Genre genre = genreService.getByName(name);
        if (genre == null) {
            long genreId = genreService.addGenre(name);
            genre = new Genre(genreId, name);
        }
        return genre;
    }
}
