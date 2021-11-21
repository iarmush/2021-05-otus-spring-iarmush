package ru.otus.lesson.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.jpa.Book;

public interface JpaBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

}
