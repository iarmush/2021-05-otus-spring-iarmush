package ru.otus.lesson.service;

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
    public long addAuthor(String name) {
        Author author = new Author(name);
        return authorDao.insert(author);
    }

    @Override
    public Author getByName(String name) {
        return authorDao.getByName(name);
    }
}
