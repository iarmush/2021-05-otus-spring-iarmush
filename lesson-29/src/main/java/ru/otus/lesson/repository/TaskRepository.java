package ru.otus.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByName(String name);

}
