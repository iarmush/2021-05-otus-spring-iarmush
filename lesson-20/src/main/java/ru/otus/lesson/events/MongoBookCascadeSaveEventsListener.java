package ru.otus.lesson.events;

import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.repository.AuthorRepository;
import ru.otus.lesson.repository.GenreRepository;

@Component
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public MongoBookCascadeSaveEventsListener(AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        Book book = event.getSource();

        if (!book.getAuthors().isEmpty()) {
            book.getAuthors().stream().filter(a -> Objects.isNull(a.getId())).forEach(authorRepository::save);
        }
        if (!book.getGenres().isEmpty()) {
            book.getGenres().stream().filter(g -> Objects.isNull(g.getId())).forEach(genreRepository::save);
        }
    }
}
