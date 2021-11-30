package ru.otus.lesson.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.otus.lesson.dto.AuthorDto;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.GenreDto;
import ru.otus.lesson.dto.NewBookDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testBooks() {
        BookDto[] bookDtos = restTemplate.getForObject("http://localhost:" + port + "/books", BookDto[].class);

        Assertions.assertTrue(bookDtos.length > 0);
    }

    @Test
    void testGenres() {
        GenreDto[] genreDtos = restTemplate.getForObject("http://localhost:" + port + "/genres", GenreDto[].class);

        Assertions.assertTrue(genreDtos.length > 0);
    }

    @Test
    void testAuthors() {
        AuthorDto[] authorDtos = restTemplate.getForObject("http://localhost:" + port + "/authors", AuthorDto[].class);

        Assertions.assertTrue(authorDtos.length > 0);
    }

    @Test
    void testBook() {
        NewBookDto newBookDto = new NewBookDto("id", "title", "authorId", "genreId");
        HttpEntity<NewBookDto> requestEntity = new HttpEntity<>(newBookDto);

        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/book", HttpMethod.POST, requestEntity,
            Void.class);

        Assertions.assertTrue(response.getStatusCode().is5xxServerError());
    }
}
