package ru.otus.lesson.config;

import java.util.Collection;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.lesson.domain.Estimation;
import ru.otus.lesson.domain.Task;
import ru.otus.lesson.dto.EstimationDto;

@MessagingGateway
public interface ScrumPoker {

    @Gateway(requestChannel = "taskChannel")
    void processTask(Task task);

    @Gateway(requestChannel = "estimationChannel", replyChannel = "resultDataChannel")
    Collection<Estimation> processEstimation(Collection<EstimationDto> estimations);
}
