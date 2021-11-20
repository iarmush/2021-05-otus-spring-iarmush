package ru.otus.lesson.dto;

import java.util.ArrayList;
import java.util.List;
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
    private Author author;
    private Genre genre;
    private List<Comment> comments = new ArrayList<>();
}
