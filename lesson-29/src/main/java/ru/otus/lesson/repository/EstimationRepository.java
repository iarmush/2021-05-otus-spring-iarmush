package ru.otus.lesson.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.lesson.domain.Estimation;

public interface EstimationRepository extends JpaRepository<Estimation, Long> {

    List<Estimation> findAllByTaskId(Long taskId);

    @Query("SELECT AVG(e.value) FROM Estimation e WHERE e.task.name = ?1")
    Integer averageValueByTaskName(String taskName);
}
