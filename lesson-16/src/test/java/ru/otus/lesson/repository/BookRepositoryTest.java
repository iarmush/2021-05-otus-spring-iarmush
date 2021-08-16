package ru.otus.lesson.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan("ru.otus.lesson.events")
@DisplayName("BookRepository должен")
class BookRepositoryTest {
    private static final String TITLE = "b1";
    private static final String NAME = "g1";
    private static final String FULL_NAME = "a1";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("должен доставать книгу по title")
    public void shouldFindByTitle() {
        Book book = bookRepository.findByTitle(TITLE).orElseThrow();

        assertThat(book).isNotNull();
    }

    @Test
    @DisplayName("должен доставать жанр по name")
    public void shouldFindByName() {
        Genre genre = genreRepository.findByName(NAME);

        assertThat(genre).isNotNull();
    }

    @Test
    @DisplayName("должен доставать автора по fullName")
    public void shouldFindByFullName() {
        Author author = authorRepository.findByFullName(FULL_NAME);

        assertThat(author).isNotNull();
    }
}
