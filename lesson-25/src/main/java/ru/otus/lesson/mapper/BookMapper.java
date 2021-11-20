package ru.otus.lesson.mapper;

import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;
import ru.otus.lesson.dto.BookDto;
import ru.otus.lesson.dto.NewBookDto;

public interface BookMapper {

    static BookDto mapBookToBookDto(Book book) {
        return new BookDto(String.valueOf(book.getId()), book.getTitle(), book.getAuthor(),
            book.getGenre(), book.getCommentList());
    }

    static Book mapNewBookDtoToBook(NewBookDto bookDto) {
        return new Book(bookDto.getTitle(), new Author(bookDto.getAuthorFullName()),
            new Genre(bookDto.getGenreName()));
    }
}
