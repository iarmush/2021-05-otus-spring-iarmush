package ru.otus.lesson.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {
    private static final String TITLE = "title";
    private static final String AUTHOR_ID = "author_id";
    private static final String GENRE_ID = "genre_id";
    private static final String FULL_NAME = "full_name";
    private static final String NAME = "name";
    private static final String ID = "id";
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of(
                AUTHOR_ID, book.getAuthor().getId(),
                GENRE_ID, book.getGenre().getId(),
                TITLE, book.getTitle());

        namedParameterJdbcOperations.update("insert into book(title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                new MapSqlParameterSource(params), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<Book> selectAll() {
        return namedParameterJdbcOperations.query(
                "select b.id, b.title, b.author_id, a.full_name, b.genre_id, g.name from book b " +
                        "inner join author a on (b.author_id = a.id) " +
                        "inner join genre g on (b.genre_id = g.id) ", new BookMapper());
    }

    @Override
    public Optional<Book> selectByTitle(String title) {
        Map<String, Object> params = Map.of(
                TITLE, title);

        try {
            return Optional.of(namedParameterJdbcOperations.queryForObject(
                    "select b.id, b.title, b.author_id, a.full_name, b.genre_id, g.name from book b " +
                            "inner join author a on (b.author_id = a.id) " +
                            "inner join genre g on (b.genre_id = g.id) " +
                            "where b.title = :title ", params, new BookMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = Map.of(
                AUTHOR_ID, book.getAuthor().getId(),
                GENRE_ID, book.getGenre().getId(),
                TITLE, book.getTitle());

        namedParameterJdbcOperations.update("update book set author_id = :author_id, genre_id = :genre_id where title = :title ", params);
    }

    @Override
    public void deleteByTitle(String title) {
        Map<String, Object> params = Map.of(TITLE, title);


        namedParameterJdbcOperations.update("delete from book where title = :title ", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong(ID);
            String name = resultSet.getString(TITLE);

            long authId = resultSet.getLong(AUTHOR_ID);
            String authFullName = resultSet.getNString(FULL_NAME);
            Author author = new Author(authId, authFullName);

            long genreId = resultSet.getLong(GENRE_ID);
            String genreName = resultSet.getNString(NAME);
            Genre genre = new Genre(genreId, genreName);

            return new Book(id, name, author, genre);
        }
    }
}
