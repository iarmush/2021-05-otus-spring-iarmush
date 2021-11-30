package ru.otus.lesson.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lesson.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Query(" select c from Comment c " +
        " join fetch c.book b " +
        " join fetch b.genre g " +
        " join fetch b.author a ")
    List<Comment> selectAll();

    Optional<Comment> findByText(@Param("text") String text);

    void deleteByText(String text);
}
