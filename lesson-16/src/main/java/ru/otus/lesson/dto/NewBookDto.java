package ru.otus.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.domain.Genre;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewBookDto {
    private String id;
    private String title;
    private String authorFullName;
    private String genreName;
}
