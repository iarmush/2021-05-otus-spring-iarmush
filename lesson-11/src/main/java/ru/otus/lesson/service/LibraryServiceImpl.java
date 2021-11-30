package ru.otus.lesson.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.domain.Genre;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    public LibraryServiceImpl(BookService bookService, AuthorService authorService, GenreService genreService,
        CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @Override
    @Transactional
    public void addBook(String title, String fullName, String name) {
        Book book = new Book(title, new Author(fullName), new Genre(name));

        bookService.create(book);
    }

    @Override
    @Transactional(readOnly = true)
    public void readAllBooks() {
        List<Book> bookList = bookService.selectAll();
        bookList.forEach(book -> {
            System.out.println(book);
            book.getCommentList().forEach(System.out::println);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public void readBookByTitle(String title) {
        Book book = bookService.selectByTitle(title);
        System.out.println(book);
        book.getCommentList().forEach(System.out::println);
    }

    @Override
    @Transactional
    public void updateBook(String title, String fullName, String name) {
        Book book = bookService.selectByTitle(title);

        Author author = checkAuthorExistOrCreate(fullName);
        Genre genre = checkGenreExistOrCreate(name);
        book.setAuthor(author);
        book.setGenre(genre);

        bookService.update(book);
    }

    @Override
    @Transactional
    public void deleteBook(String title) {
        bookService.delete(title);
    }

    @Override
    @Transactional
    public void createComment(String text, String title) {
        Book book = bookService.selectByTitle(title);

        Comment comment = new Comment(text, book);
        commentService.create(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public void readAllComments() {
        List<Comment> commentList = commentService.selectAll();
        commentList.forEach(System.out::println);
    }

    @Override
    @Transactional(readOnly = true)
    public void readComment(String text) {
        Comment comment = commentService.selectByText(text);
        System.out.println(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public void readCommentByBookTitle(String title) {
        Book book = bookService.selectByTitle(title);
        book.getCommentList().forEach(System.out::println);
    }

    @Override
    @Transactional
    public void updateComment(String text, String newText) {
        commentService.update(text, newText);
    }

    @Override
    @Transactional
    public void deleteComment(String text) {
        commentService.delete(text);
    }

    private Author checkAuthorExistOrCreate(String fullName) {
        return authorService.selectByFullName(fullName).orElseGet(() -> {
            authorService.create(fullName);

            return authorService.selectByFullName(fullName).orElseThrow();
        });
    }

    private Genre checkGenreExistOrCreate(String name) {
        return genreService.selectByName(name).orElseGet(() -> {
            genreService.create(name);
            return genreService.selectByName(name).orElseThrow();
        });
    }
}
