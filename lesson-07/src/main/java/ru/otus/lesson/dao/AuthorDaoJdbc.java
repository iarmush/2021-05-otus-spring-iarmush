package ru.otus.lesson.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.lesson.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private static final String FULL_NAME = "full_name";
    private static final String ID = "id";
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of(FULL_NAME, author.getFullName());

        namedParameterJdbcOperations.update("insert into author(full_name) values (:full_name)",
                new MapSqlParameterSource(params), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = Map.of(FULL_NAME, name);

        try {
            return namedParameterJdbcOperations.queryForObject("select id, full_name from author where full_name = :full_name",
                    new MapSqlParameterSource(params), new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong(ID);
            String name = resultSet.getString(FULL_NAME);
            return new Author(id, name);
        }
    }
}
