package ru.otus.lesson.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Estimation;
import ru.otus.lesson.domain.Task;
import ru.otus.lesson.domain.TaskStatus;
import ru.otus.lesson.dto.EstimationDto;
import ru.otus.lesson.repository.EstimationRepository;
import ru.otus.lesson.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class EstimationServiceImpl implements EstimationService {

    private final EstimationRepository estimationRepository;
    private final TaskRepository taskRepository;
    private final DtoMapper dtoMapper;

    @Override
    public Estimation processEstimation(EstimationDto estimationDto) {
        String taskName = estimationDto.getTaskName();
        Task task = taskRepository.findByName(taskName);
        Integer estimationCount = task.getEstimationCount();

        List<Estimation> estimationList = estimationRepository.findAllByTaskId(task.getId());
        int estimationSize = estimationList.size();
        boolean isTheLastTask = estimationCount - estimationSize == 1;

        if (!task.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
            taskRepository.save(task);
        }

        Estimation estimation = dtoMapper.estimationDtoToEstimation(estimationDto);
        estimation.setTask(task);
        estimationRepository.save(estimation);

        if (isTheLastTask) {
            Integer result = estimationRepository.averageValueByTaskName(taskName);
            task.setResult(result);
            task.setTaskStatus(TaskStatus.FINISHED);
            taskRepository.save(task);
        }

        return estimation;
    }
}
