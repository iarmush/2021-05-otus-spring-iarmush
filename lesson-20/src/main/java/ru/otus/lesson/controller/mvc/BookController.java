package ru.otus.lesson.controller.mvc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class BookController {

    @GetMapping("/")
    public Mono<String> pageStart() {
        return Mono.just("index");
    }
}
