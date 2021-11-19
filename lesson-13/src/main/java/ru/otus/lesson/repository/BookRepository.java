package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByTitle(String title);

    void deleteByTitle(String title);
}
