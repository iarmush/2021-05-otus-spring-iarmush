package ru.otus.lesson.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "estimation_count", nullable = false)
    private Integer estimationCount;

    @Column(name = "status", nullable = false)
    private TaskStatus taskStatus;

    @Column(name = "result")
    private Integer result;
}
