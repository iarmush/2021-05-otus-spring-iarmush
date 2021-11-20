package ru.otus.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
