package ru.otus.lesson.service;

import ru.otus.lesson.domain.Genre;

import java.util.Optional;

public interface GenreService {
    void create(String name);

    Optional<Genre> selectByName(String name);
}
