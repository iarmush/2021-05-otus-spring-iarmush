package ru.otus.lesson.service;

import ru.otus.lesson.domain.Author;

import java.util.Optional;

public interface AuthorService {

    void create(String name);

    Optional<Author> selectByFullName(String fullName);
}
