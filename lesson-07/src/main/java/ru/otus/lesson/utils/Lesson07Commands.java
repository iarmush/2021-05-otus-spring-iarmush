package ru.otus.lesson.utils;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.lesson.service.ShellFacadeService;

@ShellComponent
public class Lesson07Commands {

    private final ShellFacadeService shellFacadeService;

    public Lesson07Commands(ShellFacadeService shellFacadeService) {
        this.shellFacadeService = shellFacadeService;
    }

    @ShellMethod(value = "Add book", key = {"create"})
    public void addBook(String title, String fullName, String name) {
        shellFacadeService.addBook(title, fullName, name);
    }

    @ShellMethod(value = "Read all books", key = {"read-all"})
    public void readAllBooks() {
        shellFacadeService.readAllBooks();
    }

    @ShellMethod(value = "Read book by title", key = {"read"})
    public void readBookByTitle(String title) {
        shellFacadeService.readBookByTitle(title);
    }

    @ShellMethod(value = "Update book", key = {"update"})
    public void updateBook(String title, String fullName, String name) {
        shellFacadeService.updateBook(title, fullName, name);
    }

    @ShellMethod(value = "Delete book", key = {"delete"})
    public void deleteBook(String title) {
        shellFacadeService.deleteBook(title);
    }
}
