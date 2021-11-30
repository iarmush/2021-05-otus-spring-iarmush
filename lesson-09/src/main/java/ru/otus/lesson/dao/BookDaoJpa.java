package ru.otus.lesson.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Book;

@Service
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Book book) {
        entityManager.persist(book);
    }

    @Override
    public List<Book> selectAll() {
        return entityManager.createQuery("select b from Book b", Book.class)
/*
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("book-with-author-and-genre-and-comments"))
*/
            .getResultList();
    }

    @Override
    public Optional<Book> selectByTitle(String title) {
        try {
            return Optional.ofNullable(entityManager.createQuery("select b from Book b where b.title = :title", Book.class)
                .setParameter("title", title)
/*
                    example of n+1 solution
                    .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("book-with-author-and-genre-and-comments"))
*/
                .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    public void remove(Book book) {
        entityManager.remove(book);
        entityManager.flush();
    }
}
