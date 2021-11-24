package ru.otus.lesson.service;

import org.mapstruct.Mapper;
import ru.otus.lesson.domain.Estimation;
import ru.otus.lesson.domain.Task;
import ru.otus.lesson.dto.EstimationDto;
import ru.otus.lesson.dto.TaskDto;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    Task taskDtoToTask(TaskDto dto);

    Estimation estimationDtoToEstimation(EstimationDto dto);

}
