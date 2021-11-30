package ru.otus.lesson.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);
}
