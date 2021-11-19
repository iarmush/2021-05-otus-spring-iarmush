package ru.otus.lesson.service;

public interface ShellFacadeService {

    void addBook(String title, String fullName, String name);

    void readAllBooks();

    void readBookByTitle(String title);

    void updateBook(String title, String fullName, String name);

    void deleteBook(String title);
}
