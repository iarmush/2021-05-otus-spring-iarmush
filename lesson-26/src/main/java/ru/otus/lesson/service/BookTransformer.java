package ru.otus.lesson.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.otus.lesson.domain.jpa.Author;
import ru.otus.lesson.domain.jpa.Book;
import ru.otus.lesson.domain.jpa.Comment;
import ru.otus.lesson.domain.jpa.Genre;

@Component
public interface BookTransformer {

    static Book transform(ru.otus.lesson.domain.mongo.Book book) {
        Set<ru.otus.lesson.domain.mongo.Author> authors = book.getAuthors();
        Set<ru.otus.lesson.domain.mongo.Genre> genres = book.getGenres();
        List<ru.otus.lesson.domain.mongo.Comment> comments = book.getComments();

        if (authors.isEmpty() || genres.isEmpty()) {
            throw new RuntimeException("Authors or genres is empty");
        }

        var jpaBook = new Book();

        var author = new Author(authors.iterator().next().getFullName());
        var genre = new Genre(genres.iterator().next().getName());
        var jpaComments = comments.stream()
            .map(comment -> new Comment(comment.getText(), jpaBook)).collect(Collectors.toList());

        jpaBook.setTitle(book.getTitle());
        jpaBook.setAuthor(author);
        jpaBook.setGenre(genre);
        jpaBook.setCommentList(jpaComments);

        return jpaBook;
    }
}
