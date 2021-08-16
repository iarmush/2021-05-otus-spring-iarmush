package ru.otus.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;
import ru.otus.lesson.domain.Genre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String id;
    private String title;
    private Set<Author> authors = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();
    private List<Comment> comments = new ArrayList<>();

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(),
                book.getGenres(), book.getComments());
    }

    public static Book toDomain(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthors(),
                bookDto.getGenres(), bookDto.getComments());
    }
}
