package ru.otus.lesson.dao;

import ru.otus.lesson.domain.Author;

public interface AuthorDao {

    long insert(Author author);

    Author getByName(String name);
}
