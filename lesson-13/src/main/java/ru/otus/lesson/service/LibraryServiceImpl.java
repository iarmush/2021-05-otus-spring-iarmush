package ru.otus.lesson.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.domain.Genre;

import java.util.List;
import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final BookService bookService;

    public LibraryServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public void addBook(String title, String fullName, String name) {
        Book book = new Book();
        Author author = new Author(fullName);
        Genre genre = new Genre(name);
        book.setTitle(title);
        book.addAuthor(author);
        book.addGenre(genre);

        bookService.create(book);
    }

    @Override
    @Transactional(readOnly = true)
    public void readAllBooks() {
        List<Book> bookList = bookService.selectAll();
        bookList.forEach(System.out::println);
    }

    @Override
    @Transactional(readOnly = true)
    public void readBookByTitle(String title) {
        Book book = bookService.selectByTitle(title);
        System.out.println(book);
    }

    @Override
    @Transactional
    public void updateBook(String title, String fullName, String name) {
        Book book = bookService.selectByTitle(title);

        Author author = new Author(fullName);
        Genre genre = new Genre(name);
        book.setAuthors(Set.of(author));
        book.setGenres(Set.of(genre));

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

        book.addComment(new Comment(text));

        bookService.update(book);
    }

    @Override
    @Transactional(readOnly = true)
    public void readCommentByBookTitle(String title) {
        Book book = bookService.selectByTitle(title);

        book.getComments().forEach(System.out::println);
    }

    @Override
    @Transactional
    public void updateCommentByBookTitle(String title, String text, String newText) {
        Book book = bookService.selectByTitle(title);

        Comment newComment = new Comment(newText);

        List<Comment> comments = book.getComments();
        if (comments.removeIf(comment -> comment.getText().equals(text))) {
            comments.add(newComment);

        }

        bookService.update(book);
    }

    @Override
    @Transactional
    public void deleteCommentByBookTitle(String title, String text) {
        Book book = bookService.selectByTitle(title);

        book.getComments().removeIf(comment -> comment.getText().equals(text));

        bookService.update(book);
    }

    @Override
    @Transactional
    public void deleteAllCommentsByBookTitle(String title) {
        Book book = bookService.selectByTitle(title);

        book.getComments().clear();

        bookService.update(book);
    }
}
