package ru.otus.lesson.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Comment;

@Service
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> selectAll() {
        return entityManager.createQuery("select c from Comment c " +
                "join fetch c.book b " +
                "join fetch b.genre g " +
                "join fetch b.author a ", Comment.class)
            .getResultList();
    }

    @Override
    public Optional<Comment> selectByText(String text) {
        return Optional.ofNullable(entityManager.createQuery("select c from Comment c " +
                "join fetch c.book b " +
                "join fetch b.genre g " +
                "join fetch b.author a " +
                "where c.text = :text", Comment.class)
            .setParameter("text", text)
            .getSingleResult());
    }

    @Override
    public void update(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    public void remove(Comment comment) {
        entityManager.remove(comment);
    }
}
