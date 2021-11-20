package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lesson.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
