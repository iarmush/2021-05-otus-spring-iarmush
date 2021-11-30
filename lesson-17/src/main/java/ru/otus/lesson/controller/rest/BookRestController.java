package ru.otus.lesson.controller.rest;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.mapper.BookMapper;
import ru.otus.lesson.service.BookService;

@RestController
@AllArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookDto> pageListBook() {
        return bookService.selectAll().stream()
            .map(BookMapper::mapBookToBookDto)
            .collect(Collectors.toList());
    }
}
