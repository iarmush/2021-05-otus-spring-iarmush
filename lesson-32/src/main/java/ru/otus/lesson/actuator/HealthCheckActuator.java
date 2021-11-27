package ru.otus.lesson.actuator;

import java.util.concurrent.atomic.AtomicBoolean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.lesson.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class HealthCheckActuator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health getHealth(boolean includeDetails) {
        AtomicBoolean bookPresent = new AtomicBoolean();
        long count = bookRepository.count();
        bookPresent.set(count > 0);

        return bookPresent.get() ? Health.up().build() : Health.down().build();
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
}
