package ru.otus.lesson.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lesson.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
}
