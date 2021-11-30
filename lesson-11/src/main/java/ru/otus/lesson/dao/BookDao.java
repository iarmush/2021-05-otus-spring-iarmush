package ru.otus.lesson.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Book;

public interface BookDao extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    void deleteByTitle(String title);
}
