package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
