package ru.otus.lesson.service;

import java.util.Optional;
import ru.otus.lesson.domain.Author;

public interface AuthorService {

    long create(String name);

    Optional<Author> selectByName(String name);
}
