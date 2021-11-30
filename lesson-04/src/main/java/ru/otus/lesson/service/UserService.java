package ru.otus.lesson.service;

import ru.otus.lesson.domain.User;

public interface UserService {

    void enterUserName();

    void enterUserSurname();

    User getUser();
}
