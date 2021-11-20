package ru.otus.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
