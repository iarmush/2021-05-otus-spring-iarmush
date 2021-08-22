package ru.otus.lesson.mapper;

import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.NewBookDto;

import java.util.Collections;
import java.util.Set;

public interface BookMapper {
    static BookDto mapBookToBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(),
                book.getGenres(), book.getComments());
    }

    static Book mapNewBookDtoToBook(NewBookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), Set.of(new Author(bookDto.getAuthorFullName())),
                Set.of(new Genre(bookDto.getGenreName())), Collections.emptyList());
    }
}
