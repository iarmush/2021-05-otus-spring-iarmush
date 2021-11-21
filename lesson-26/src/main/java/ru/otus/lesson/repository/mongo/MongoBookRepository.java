package ru.otus.lesson.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.mongo.Book;

public interface MongoBookRepository extends MongoRepository<Book, String> {

}
