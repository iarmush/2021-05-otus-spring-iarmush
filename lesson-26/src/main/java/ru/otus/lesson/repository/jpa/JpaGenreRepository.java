package ru.otus.lesson.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.jpa.Genre;

public interface JpaGenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);
}
