package ru.otus.lesson.service;

import java.util.Scanner;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.User;
import ru.otus.lesson.utils.MessageSourceExam;
import ru.otus.lesson.utils.OutputScanner;

@Service
public class UserServiceImpl implements UserService {

    private final User user = new User();
    private final OutputScanner outputScanner;
    private final MessageSourceExam messageSourceExam;

    public UserServiceImpl(OutputScanner outputScanner, MessageSourceExam messageSourceExam) {
        this.outputScanner = outputScanner;
        this.messageSourceExam = messageSourceExam;
    }

    @Override
    public void enterUserName() {
        messageSourceExam.printEnterUserName();
        Scanner scanner = new Scanner(outputScanner.getConsoleOutput());
        user.setName(scanner.nextLine());
    }

    @Override
    public void enterUserSurname() {
        messageSourceExam.printEnterUserSurname();
        Scanner scanner = new Scanner(outputScanner.getConsoleOutput());
        user.setSurname(scanner.nextLine());
    }

    @Override
    public User getUser() {
        return user;
    }
}
