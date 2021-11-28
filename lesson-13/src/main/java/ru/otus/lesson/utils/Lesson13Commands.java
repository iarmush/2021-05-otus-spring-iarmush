package ru.otus.lesson.utils;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lesson.service.LibraryService;

@ShellComponent
public class Lesson13Commands {

    private final LibraryService libraryService;

    public Lesson13Commands(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod(value = "Add book", key = {"create-book"})
    public void addBook(String title, String fullName, String name) {
        libraryService.addBook(title, fullName, name);
    }

    @ShellMethod(value = "Read all books", key = {"read-all-books"})
    public void readAllBooks() {
        libraryService.readAllBooks();
    }

    @ShellMethod(value = "Read book by title", key = {"read-book"})
    public void readBookByTitle(String title) {
        libraryService.readBookByTitle(title);
    }

    @ShellMethod(value = "Update book", key = {"update-book"})
    public void updateBook(String title, String fullName, String name) {
        libraryService.updateBook(title, fullName, name);
    }

    @ShellMethod(value = "Delete book", key = {"delete-book"})
    public void deleteBook(String title) {
        libraryService.deleteBook(title);
    }

    @ShellMethod(value = "Create comment to book", key = {"create-comment"})
    public void createComment(String text, String title) {
        libraryService.createComment(text, title);
    }

    @ShellMethod(value = "Read comment by book title", key = {"read-comment-by-book-title"})
    public void readCommentByBookTitle(String title) {
        libraryService.readCommentByBookTitle(title);
    }

    @ShellMethod(value = "Update comment by book title", key = {"update-comment-by-book-title"})
    public void updateCommentByBookTitle(String title, String text, String newText) {
        libraryService.updateCommentByBookTitle(title, text, newText);
    }

    @ShellMethod(value = "Delete comment by book title", key = {"delete-comment-by-book-title"})
    public void deleteCommentByBookTitle(String title, String text) {
        libraryService.deleteCommentByBookTitle(title, text);
    }

    @ShellMethod(value = "Delete all comments by book title", key = {"read-all-comments-by-book-title"})
    public void deleteAllCommentsByBookTitle(String title) {
        libraryService.deleteAllCommentsByBookTitle(title);
    }
}
