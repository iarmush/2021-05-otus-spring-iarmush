package ru.otus.lesson.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import ru.otus.lesson.dto.AuthorDto;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.GenreDto;
import ru.otus.lesson.dto.NewBookDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookRestControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void testBooks() {
        webClient.get()
            .uri("/books")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(BookDto.class);
    }

    @Test
    void testGenres() {
        webClient.get()
            .uri("/genres")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(GenreDto.class);
    }

    @Test
    void testAuthors() {
        webClient.get()
            .uri("/authors")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(AuthorDto.class);
    }

    @Test
    void testBook() {
        NewBookDto newBookDto = new NewBookDto("id", "title", "authorId", "genreId");

        webClient.post()
            .uri("/book")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(newBookDto))
            .exchange()
            .expectStatus().isOk();
    }
}
