package ru.otus.lesson.service;

import ru.otus.lesson.domain.Author;

public interface AuthorService {

    long addAuthor(String name);

    Author getByName(String name);
}
