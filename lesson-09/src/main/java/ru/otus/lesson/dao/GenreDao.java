package ru.otus.lesson.dao;

import java.util.Optional;
import ru.otus.lesson.domain.Genre;

public interface GenreDao {

    long create(String name);

    Optional<Genre> selectByName(String name);
}
