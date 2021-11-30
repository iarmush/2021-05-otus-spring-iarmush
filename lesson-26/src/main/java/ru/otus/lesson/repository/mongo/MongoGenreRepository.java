package ru.otus.lesson.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.mongo.Genre;

public interface MongoGenreRepository extends MongoRepository<Genre, String> {

}
