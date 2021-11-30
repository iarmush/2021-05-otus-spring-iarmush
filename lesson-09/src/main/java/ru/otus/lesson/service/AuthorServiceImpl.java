package ru.otus.lesson.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.AuthorDao;
import ru.otus.lesson.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public long create(String name) {
        return authorDao.create(name);
    }

    @Override
    public Optional<Author> selectByName(String name) {
        return authorDao.selectByName(name);
    }
}
