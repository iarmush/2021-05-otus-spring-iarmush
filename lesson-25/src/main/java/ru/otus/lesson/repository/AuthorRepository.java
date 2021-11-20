package ru.otus.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFullName(String fullName);
}
