package ru.otus.lesson.dao;

import ru.otus.lesson.domain.Genre;

public interface GenreDao {
    long insert(Genre genre);

    Genre getByName(String name);
}
