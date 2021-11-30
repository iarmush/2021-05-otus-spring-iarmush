package ru.otus.lesson.utils;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lesson.service.LibraryService;

@ShellComponent
public class Lesson11Commands {

    private final LibraryService libraryService;

    public Lesson11Commands(LibraryService libraryService) {
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

    @ShellMethod(value = "Read all comments", key = {"read-all-comments"})
    public void readAllComments() {
        libraryService.readAllComments();
    }

    @ShellMethod(value = "Read comment", key = {"read-comment"})
    public void readComment(String text) {
        libraryService.readComment(text);
    }

    @ShellMethod(value = "Read comment by book title", key = {"read-comment-by-book-title"})
    public void readCommentByBookTitle(String title) {
        libraryService.readCommentByBookTitle(title);
    }

    @ShellMethod(value = "Update comment", key = {"update-comment"})
    public void updateComment(String text, String newText) {
        libraryService.updateComment(text, newText);
    }

    @ShellMethod(value = "Delete comment", key = {"delete-comment"})
    public void deleteComment(String text) {
        libraryService.deleteComment(text);
    }
}
