package ru.otus.lesson.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.jpa.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

}
