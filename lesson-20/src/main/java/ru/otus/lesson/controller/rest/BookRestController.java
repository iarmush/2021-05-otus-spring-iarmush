package ru.otus.lesson.controller.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.dto.AuthorDto;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.GenreDto;
import ru.otus.lesson.dto.NewBookDto;
import ru.otus.lesson.mapper.BookMapper;
import ru.otus.lesson.repository.AuthorRepository;
import ru.otus.lesson.repository.BookRepository;
import ru.otus.lesson.repository.GenreRepository;

@RestController
@Slf4j
@AllArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping("/books")
    public Flux<BookDto> books() {
        return bookRepository.findAll().map(BookMapper::mapBookToBookDto);
    }

    @GetMapping("/genres")
    public Flux<GenreDto> genres() {
        return genreRepository.findAll().map(BookMapper::mapGenreToGenreDto);
    }

    @GetMapping("/authors")
    public Flux<AuthorDto> authors() {
        return authorRepository.findAll().map(BookMapper::mapAuthorToAuthorDto);
    }

    @PostMapping("/book")
    public Mono<Void> book(@RequestBody NewBookDto newBookDto) {
        return Mono.zip(authorRepository.findById(newBookDto.getAuthorId()),
                genreRepository.findById(newBookDto.getGenreId()))
            .zipWhen(data -> {
                Book book = new Book();
                book.addAuthor(data.getT1());
                book.addGenre(data.getT2());
                book.setTitle(newBookDto.getTitle());

                return bookRepository.save(book).map(BookMapper::mapBookToBookDto);
            }).then(Mono.empty());
    }
}
