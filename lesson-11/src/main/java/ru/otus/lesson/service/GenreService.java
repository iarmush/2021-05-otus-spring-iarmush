package ru.otus.lesson.service;

import java.util.Optional;
import ru.otus.lesson.domain.Genre;

public interface GenreService {

    void create(String name);

    Optional<Genre> selectByName(String name);
}
