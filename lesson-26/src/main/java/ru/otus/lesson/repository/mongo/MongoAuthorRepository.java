package ru.otus.lesson.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.mongo.Author;

public interface MongoAuthorRepository extends MongoRepository<Author, String> {

}
