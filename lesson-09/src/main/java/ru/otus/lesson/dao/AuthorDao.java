package ru.otus.lesson.dao;

import java.util.Optional;
import ru.otus.lesson.domain.Author;

public interface AuthorDao {

    long create(String fullName);

    Optional<Author> selectByName(String fullName);
}
