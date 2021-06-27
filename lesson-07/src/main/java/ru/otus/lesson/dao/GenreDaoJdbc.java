package ru.otus.lesson.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.lesson.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Repository
public class GenreDaoJdbc implements GenreDao {
    public static final String NAME = "name";
    public static final String ID = "id";
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of(NAME, genre.getName());

        namedParameterJdbcOperations.update("insert into genre(name) values (:name)",
                new MapSqlParameterSource(params), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Map.of(NAME, name);

        try {
            return namedParameterJdbcOperations.queryForObject("select id, name from genre where name = :name",
                    new MapSqlParameterSource(params), new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong(ID);
            String name = resultSet.getString(NAME);
            return new Genre(id, name);
        }
    }
}
