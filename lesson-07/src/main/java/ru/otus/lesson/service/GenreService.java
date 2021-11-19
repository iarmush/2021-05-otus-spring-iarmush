package ru.otus.lesson.service;

import ru.otus.lesson.domain.Genre;

public interface GenreService {
    long addGenre(String name);

    Genre getByName(String name);
}
