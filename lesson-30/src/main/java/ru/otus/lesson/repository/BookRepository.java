package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
