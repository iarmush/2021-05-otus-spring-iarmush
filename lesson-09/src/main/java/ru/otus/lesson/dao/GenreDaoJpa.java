package ru.otus.lesson.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Genre;

@Service
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(String name) {
        Genre genre = new Genre(name);

        entityManager.persist(genre);

        return genre.getId();
    }

    @Override
    public Optional<Genre> selectByName(String name) {
        try {
            return Optional.ofNullable(entityManager.createQuery("select g from Genre g where name = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
