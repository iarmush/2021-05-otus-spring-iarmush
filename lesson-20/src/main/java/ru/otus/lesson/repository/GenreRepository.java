package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.lesson.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
