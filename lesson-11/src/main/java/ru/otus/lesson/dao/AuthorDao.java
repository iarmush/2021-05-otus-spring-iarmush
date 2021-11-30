package ru.otus.lesson.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {

    Optional<Author> findByFullName(String fullName);
}
