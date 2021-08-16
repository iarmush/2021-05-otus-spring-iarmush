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

    //    public static NewBookDto toDto(Book book) {
//        return new NewBookDto(book.getId(), book.getTitle(), book.getAuthors(),
//                book.getGenres(), book.getComments());
//    }
//
    public static Book toDomain(NewBookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), Set.of(new Author(bookDto.getAuthorFullName())),
                Set.of(new Genre(bookDto.getGenreName())), Collections.emptyList());
    }
}
