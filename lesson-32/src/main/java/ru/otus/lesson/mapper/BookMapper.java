package ru.otus.lesson.mapper;

import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;
import ru.otus.lesson.dto.AuthorDto;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.GenreDto;

public interface BookMapper {

    static BookDto mapBookToBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(),
            book.getGenres(), book.getComments());
    }

    static AuthorDto mapAuthorToAuthorDto(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }

    static GenreDto mapGenreToGenreDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
