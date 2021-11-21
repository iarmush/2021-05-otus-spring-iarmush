package ru.otus.lesson.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.domain.Genre;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.NewBookDto;
import ru.otus.lesson.mapper.BookMapper;
import ru.otus.lesson.repository.AuthorRepository;
import ru.otus.lesson.repository.BookRepository;
import ru.otus.lesson.repository.CommentRepository;
import ru.otus.lesson.repository.GenreRepository;

@Controller
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    private static final String START_PAGE = "index";
    private static final String CREATE_PAGE = "create";
    private static final String VIEW_PAGE = "view";
    private static final String LIST_PAGE = "list";

    @GetMapping("/")
    public String pageStart() {
        return START_PAGE;
    }

    @GetMapping("book/search/{title}")
    public String pageViewBook(@PathVariable String title, Model model) {
        Book book = bookRepository.findByTitle(title).orElseThrow();

        model.addAttribute("book", BookMapper.mapBookToBookDto(book));
        model.addAttribute("comment", new Comment());
        return VIEW_PAGE;
    }

    @GetMapping("book/list")
    public String pageListBook(Model model) {
        List<BookDto> books = bookRepository.findAll().stream()
            .map(BookMapper::mapBookToBookDto)
            .collect(Collectors.toList());

        model.addAttribute("books", books);
        return LIST_PAGE;
    }

    @GetMapping("book/create")
    public String pageCreateBook(Model model) {
        List<String> authorsFullName = authorRepository.findAll().stream()
            .map(Author::getFullName)
            .collect(Collectors.toList());
        List<String> genresName = genreRepository.findAll().stream()
            .map(Genre::getName)
            .collect(Collectors.toList());

        model.addAllAttributes(Map.of(
            "newBook", new NewBookDto(),
            "authorsFullName", authorsFullName,
            "genresName", genresName));
        return CREATE_PAGE;
    }

    @PostMapping("book/create")
    public RedirectView createBook(NewBookDto newBookDto, Model model) {
        Author author = authorRepository.findByFullName(newBookDto.getAuthorFullName());
        Genre genre = genreRepository.findByName(newBookDto.getGenreName());
        Book book = new Book(newBookDto.getTitle(), author, genre);
        bookRepository.save(book);

        model.addAttribute("book", book);
        return new RedirectView("/", true);
    }

    @PostMapping("/book/{title}/comment")
    public RedirectView addComment(@PathVariable String title, Model model, Comment comment) {
        Book book = bookRepository.findByTitle(title).orElseThrow();
        comment.setBook(book);
        commentRepository.save(comment);

        model.addAttribute("book", BookMapper.mapBookToBookDto(book));
        return new RedirectView(String.format("/book/search/%s", title), true);
    }

    @GetMapping("book/{title}/delete")
    @Transactional
    public RedirectView deleteBook(@PathVariable String title) {
        bookRepository.deleteByTitle(title);
        return new RedirectView("/book/list", true);
    }
}
