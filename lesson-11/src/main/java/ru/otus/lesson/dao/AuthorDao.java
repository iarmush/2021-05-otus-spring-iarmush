package ru.otus.lesson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Author;

import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long> {
    Optional<Author> findByFullName(String fullName);
}
