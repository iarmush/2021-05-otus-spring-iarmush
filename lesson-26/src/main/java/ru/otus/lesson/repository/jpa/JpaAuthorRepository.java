package ru.otus.lesson.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.jpa.Author;

public interface JpaAuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFullName(String fullName);

}
