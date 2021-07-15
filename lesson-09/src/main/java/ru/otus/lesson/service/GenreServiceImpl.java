package ru.otus.lesson.service;

import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.GenreDao;
import ru.otus.lesson.domain.Genre;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public long create(String name) {
        return genreDao.create(name);
    }

    @Override
    public Optional<Genre> selectByName(String name) {
        return genreDao.selectByName(name);
    }
}
