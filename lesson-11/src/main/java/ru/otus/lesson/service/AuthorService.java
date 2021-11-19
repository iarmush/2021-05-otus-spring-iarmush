package ru.otus.lesson.service;

import java.util.Optional;
import ru.otus.lesson.domain.Author;

public interface AuthorService {

    void create(String name);

    Optional<Author> selectByFullName(String fullName);
}
