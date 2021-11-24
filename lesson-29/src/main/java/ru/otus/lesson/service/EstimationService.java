package ru.otus.lesson.service;

import ru.otus.lesson.domain.Estimation;
import ru.otus.lesson.dto.EstimationDto;

public interface EstimationService {

    Estimation processEstimation(EstimationDto estimationDto);
}
