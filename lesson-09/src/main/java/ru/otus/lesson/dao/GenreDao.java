package ru.otus.lesson.dao;

import ru.otus.lesson.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    long create(String name);

    Optional<Genre> selectByName(String name);
}
