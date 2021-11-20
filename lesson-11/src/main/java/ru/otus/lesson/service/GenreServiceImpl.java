package ru.otus.lesson.service;

import java.util.Optional;
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
    public void create(String name) {
        genreDao.save(new Genre(name));
    }

    @Override
    public Optional<Genre> selectByName(String name) {
        return genreDao.findByName(name);
    }
}
