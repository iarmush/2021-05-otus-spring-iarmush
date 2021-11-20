package ru.otus.lesson.utils;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.lesson.config.ExamConfig;
import ru.otus.lesson.domain.ExamQuestion;
import ru.otus.lesson.domain.User;

@Component
public class MessageSourceExamImpl implements MessageSourceExam {

    private static final String ENTER_USER_NAME = "Enter user name";
    private static final String ENTER_USER_SURNAME = "Enter user surname";
    private static final String USER = "User";
    private static final String OPTION_A = "option A";
    private static final String OPTION_B = "option B";
    private static final String OPTION_C = "option C";
    private static final String OPTION_D = "option D";
    private final MessageSource messageSource;
    private final Locale locale;

    public MessageSourceExamImpl(MessageSource messageSource, ExamConfig examConfig) {
        this.messageSource = messageSource;
        this.locale = new Locale(examConfig.getLanguage());
    }

    @Override
    public void printExamQuestion(ExamQuestion examQuestion) {
        System.out.println(messageSource.getMessage(
            examQuestion.getTitle(),
            new String[0],
            locale
        ));

        System.out.println(messageSource.getMessage(
            OPTION_A,
            new String[]{examQuestion.getAnswerOptions().getOptionA()},
            locale
        ));

        System.out.println(messageSource.getMessage(
            OPTION_B,
            new String[]{examQuestion.getAnswerOptions().getOptionB()},
            locale
        ));

        System.out.println(messageSource.getMessage(
            OPTION_C,
            new String[]{examQuestion.getAnswerOptions().getOptionC()},
            locale
        ));

        System.out.println(messageSource.getMessage(
            OPTION_D,
            new String[]{examQuestion.getAnswerOptions().getOptionD()},
            locale
        ));
    }

    @Override
    public void printExamResult(String result, User user) {
        System.out.println(messageSource.getMessage(
            USER,
            new String[]{user.getName(), user.getSurname()},
            locale
        ));
        System.out.println(messageSource.getMessage(
            result,
            new String[0],
            locale
        ));
    }

    @Override
    public void printEnterUserName() {
        System.out.println(messageSource.getMessage(
            ENTER_USER_NAME,
            new String[0],
            locale
        ));
    }

    @Override
    public void printEnterUserSurname() {
        System.out.println(messageSource.getMessage(
            ENTER_USER_SURNAME,
            new String[0],
            locale
        ));
    }
}
