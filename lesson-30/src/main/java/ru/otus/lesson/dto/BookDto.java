package ru.otus.lesson.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.domain.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private String id;
    private String title;
    private Set<Author> authors = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();
    private List<Comment> comments = new ArrayList<>();
}
