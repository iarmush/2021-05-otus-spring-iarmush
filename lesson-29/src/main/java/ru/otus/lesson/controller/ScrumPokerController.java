package ru.otus.lesson.controller;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lesson.config.ScrumPoker;
import ru.otus.lesson.domain.Estimation;
import ru.otus.lesson.dto.EstimationDto;
import ru.otus.lesson.dto.TaskDto;
import ru.otus.lesson.service.DtoMapper;

@RestController
@AllArgsConstructor
@Slf4j
public class ScrumPokerController {

    private final ScrumPoker scrumPoker;
    private final DtoMapper dtoMapper;

    @PostMapping("/create")
    public void create(@RequestBody TaskDto taskDto) {
        scrumPoker.processTask(dtoMapper.taskDtoToTask(taskDto));
    }

    @PutMapping("/estimate")
    public void estimate(@RequestBody List<EstimationDto> estimations) {
        Collection<Estimation> estimationList = scrumPoker.processEstimation(estimations);
        estimationList.forEach(estimation -> log.info("Receive from estimationChannel: " + estimation));
    }
}
