package ru.otus.lesson.controller.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;
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
@RequiredArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping("/books")
    @HystrixCommand(commandKey = "findAllBooks", fallbackMethod = "buildFallbackFindAllBooks")
    public List<BookDto> books() throws InterruptedException {
        if (new Random().nextInt(5) > 3) {
            Thread.sleep(5000);
        }
        return bookRepository.findAll().stream().map(BookMapper::mapBookToBookDto).collect(Collectors.toList());
    }

    @GetMapping("/genres")
    public List<GenreDto> genres() {
        return genreRepository.findAll().stream().map(BookMapper::mapGenreToGenreDto).collect(Collectors.toList());
    }

    @GetMapping("/authors")
    public List<AuthorDto> authors() {
        return authorRepository.findAll().stream().map(BookMapper::mapAuthorToAuthorDto).collect(Collectors.toList());
    }

    @PostMapping("/book")
    public void book(@RequestBody NewBookDto newBookDto) {
        Author author = authorRepository.findById(newBookDto.getAuthorId()).orElseThrow();
        Genre genre = genreRepository.findById(newBookDto.getGenreId()).orElseThrow();
        Book book = new Book();
        book.addAuthor(author);
        book.addGenre(genre);
        book.setTitle(newBookDto.getTitle());

        bookRepository.save(book);
    }

    public List<BookDto> buildFallbackFindAllBooks() {
        var author = new Author();
        author.setId("1");
        author.setFullName("Default author fullName");
        var genre = new Genre();
        genre.setId("1");
        genre.setName("Default genre namee");
        var bookDto = new BookDto();
        bookDto.setId("1");
        bookDto.setTitle("Default book title");
        bookDto.setAuthors(Set.of(author));
        bookDto.setGenres(Set.of(genre));

        return List.of(bookDto);
    }
}
