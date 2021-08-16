package ru.otus.lesson.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.dto.AuthorDto;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.NewBookDto;
import ru.otus.lesson.repository.AuthorRepository;
import ru.otus.lesson.service.BookService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    private static final String START_PAGE = "index";
    private static final String CREATE_PAGE = "create";
    private static final String VIEW_PAGE = "view";
    private static final String LIST_PAGE = "list";

    @GetMapping("/")
    public String pageStart(Model model) {
        model.addAttribute("book", new BookDto());
        return START_PAGE;
    }

    @GetMapping("book/search/{title}")
    public String pageViewBook(@PathVariable String title, Model model) {
        BookDto bookDto = BookDto.toDto(bookService.selectByTitle(title));
        model.addAttribute("book", bookDto);
        model.addAttribute("comment", new Comment());
        return VIEW_PAGE;
    }

    @GetMapping("book/list")
    public String pageListBook(Model model) {
        List<BookDto> books = bookService.selectAll().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("books", books);
        return LIST_PAGE;
    }

    @GetMapping("book/create")
    public String pageCreateBook(Model model) {
        List<AuthorDto> authors = authorRepository.findAll().stream()
                .map(AuthorDto::toDto)
                .collect(Collectors.toList());

        model.addAllAttributes(Map.of(
                "newBook", new NewBookDto()));
        return CREATE_PAGE;
    }

    @PostMapping("book/create")
    public RedirectView createBook(NewBookDto newBookDto, Model model) {
        Book savedBook = NewBookDto.toDomain(newBookDto);
        bookService.create(savedBook);
        model.addAttribute("book", savedBook);
        return new RedirectView("/", true);
    }


    @PostMapping("/book/{title}/comment")
    public RedirectView addComment(@PathVariable String title, Model model, Comment comment, Book book) {
        Book selectedBook = bookService.selectByTitle(title);
        selectedBook.addComment(comment);
        bookService.update(selectedBook);
        BookDto saved = BookDto.toDto(book);
        model.addAttribute("book", saved);
        return new RedirectView(String.format("/book/search/%s", title), true);
    }

    @GetMapping("book/{title}/delete")
    public RedirectView deleteBook(@PathVariable String title) {
        bookService.delete(title);
        return new RedirectView("/book/list", true);
    }
}
