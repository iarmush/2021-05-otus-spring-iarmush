package ru.otus.lesson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Book;

import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    void deleteByTitle(String title);
}
