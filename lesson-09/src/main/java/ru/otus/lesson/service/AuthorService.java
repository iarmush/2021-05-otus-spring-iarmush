package ru.otus.lesson.service;

import ru.otus.lesson.domain.Author;

import java.util.Optional;

public interface AuthorService {

    long create(String name);

    Optional<Author> selectByName(String name);
}
