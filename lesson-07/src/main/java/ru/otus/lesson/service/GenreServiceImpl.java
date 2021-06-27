package ru.otus.lesson.service;

import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.GenreDao;
import ru.otus.lesson.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public long addGenre(String name) {
        Genre genre = new Genre(name);
        return genreDao.insert(genre);
    }

    @Override
    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }
}
