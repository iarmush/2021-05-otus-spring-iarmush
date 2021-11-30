package ru.otus.lesson.dto;

import lombok.Data;

@Data
public class TaskDto {

    private String name;
    private Integer estimationCount;
    private String taskStatus;
    private Integer result;
}
