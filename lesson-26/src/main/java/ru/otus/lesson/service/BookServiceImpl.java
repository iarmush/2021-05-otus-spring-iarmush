package ru.otus.lesson.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.jpa.Author;
import ru.otus.lesson.domain.jpa.Book;
import ru.otus.lesson.domain.jpa.Genre;
import ru.otus.lesson.repository.jpa.JpaAuthorRepository;
import ru.otus.lesson.repository.jpa.JpaBookRepository;
import ru.otus.lesson.repository.jpa.JpaCommentRepository;
import ru.otus.lesson.repository.jpa.JpaGenreRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final JpaBookRepository jpaBookRepository;
    private final JpaAuthorRepository jpaAuthorRepository;
    private final JpaGenreRepository jpaGenreRepository;
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public List<Book> getAll() {
        return jpaBookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        var author = getOrCreateAuthor(book.getAuthor());
        var genre = getOrCreateGenre(book.getGenre());
        var bookToSave = jpaBookRepository.findByTitle(book.getTitle()).orElse(book);

        bookToSave.setTitle(book.getTitle());
        bookToSave.setAuthor(author);
        bookToSave.setGenre(genre);

        Book savedBook = jpaBookRepository.save(bookToSave);
        jpaCommentRepository.saveAll(bookToSave.getCommentList());

        return savedBook;
    }

    private Author getOrCreateAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author is null");
        }
        var authorOptional = jpaAuthorRepository.findByFullName(author.getFullName());
        return authorOptional.orElseGet(() -> jpaAuthorRepository.save(author));
    }

    private Genre getOrCreateGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre is null");
        }
        var genreOptional = jpaGenreRepository.findByName(genre.getName());
        return genreOptional.orElseGet(() -> jpaGenreRepository.save(genre));
    }
}
