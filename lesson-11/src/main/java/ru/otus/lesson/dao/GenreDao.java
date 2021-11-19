package ru.otus.lesson.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Genre;

import java.util.Optional;

public interface GenreDao extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
