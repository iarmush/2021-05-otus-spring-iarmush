package ru.otus.lesson.repository;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class BookRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldReturnListOfBookWithMatchingRate() {
        List<Author> author = authorRepository.findAll();
        List<Genre> genre = genreRepository.findAll();

        Book book = new Book();
        book.addAuthor(author.get(0));
        book.addGenre(genre.get(0));
        book.setTitle("title");

        List<Book> booksBefore = bookRepository.findAll();

        Assertions.assertEquals(3, author.size());
        Assertions.assertEquals(3, genre.size());
        Assertions.assertEquals(3, booksBefore.size());

        bookRepository.save(book);
        List<Book> booksAfter = bookRepository.findAll();

        Assertions.assertEquals(4, booksAfter.size());
    }
}
