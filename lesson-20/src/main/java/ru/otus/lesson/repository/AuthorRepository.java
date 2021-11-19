package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lesson.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}
