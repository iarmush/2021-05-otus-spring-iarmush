package ru.otus.lesson.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.domain.Estimation;
import ru.otus.lesson.domain.Task;
import ru.otus.lesson.domain.TaskStatus;
import ru.otus.lesson.dto.EstimationDto;
import ru.otus.lesson.repository.EstimationRepository;
import ru.otus.lesson.repository.TaskRepository;

class EstimationServiceImplTest {

    @Mock
    EstimationRepository estimationRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    DtoMapper dtoMapper;
    @InjectMocks
    EstimationServiceImpl estimationServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessEstimation() {
        String taskName = "JIRA_TEST";
        Task task = new Task();
        task.setId(1L);
        task.setName(taskName);
        task.setEstimationCount(2);
        task.setTaskStatus(TaskStatus.CREATED);

        Estimation estimation = new Estimation();
        estimation.setTask(task);

        EstimationDto estimationDto = new EstimationDto();
        estimationDto.setTaskName(taskName);

        when(taskRepository.findByName(taskName)).thenReturn(task);
        when(estimationRepository.findAllByTaskId(task.getId())).thenReturn(List.of(estimation));
        when(dtoMapper.estimationDtoToEstimation(estimationDto)).thenReturn(estimation);
        when(estimationRepository.averageValueByTaskName(taskName)).thenReturn(10);

        Estimation result = estimationServiceImpl.processEstimation(estimationDto);
        Assertions.assertEquals(estimation, result);

        verify(taskRepository, times(2)).save(task);
        verify(taskRepository).findByName(taskName);
        verify(estimationRepository).findAllByTaskId(1L);
        verify(dtoMapper).estimationDtoToEstimation(estimationDto);
        verify(estimationRepository).averageValueByTaskName(taskName);
    }
}
