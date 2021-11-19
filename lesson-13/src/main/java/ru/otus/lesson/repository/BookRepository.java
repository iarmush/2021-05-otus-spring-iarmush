package ru.otus.lesson.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByTitle(String title);

    void deleteByTitle(String title);
}
