package ru.otus.lesson.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.lesson.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

    void removeById(String id);

    Genre findByName(String name);
}
