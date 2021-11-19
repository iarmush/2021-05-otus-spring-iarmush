package ru.otus.lesson.dao;

import ru.otus.lesson.domain.Author;

import java.util.Optional;

public interface AuthorDao {

    long create(String fullName);

    Optional<Author> selectByName(String fullName);
}
