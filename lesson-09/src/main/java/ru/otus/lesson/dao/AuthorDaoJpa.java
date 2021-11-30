package ru.otus.lesson.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Author;

@Service
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(String fullName) {
        Author author = new Author(fullName);

        entityManager.persist(author);

        return author.getId();
    }

    @Override
    public Optional<Author> selectByName(String fullName) {
        try {
            return Optional.ofNullable(entityManager.createQuery("select a from Author a where a.fullName = :fullName", Author.class)
                .setParameter("fullName", fullName)
                .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
