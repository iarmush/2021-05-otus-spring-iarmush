package ru.otus.lesson.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.lesson.domain.mongo.Author;
import ru.otus.lesson.domain.mongo.Book;
import ru.otus.lesson.domain.mongo.Comment;
import ru.otus.lesson.domain.mongo.Genre;
import ru.otus.lesson.repository.mongo.MongoAuthorRepository;
import ru.otus.lesson.repository.mongo.MongoBookRepository;
import ru.otus.lesson.repository.mongo.MongoGenreRepository;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    private Author author1;
    private Author author2;
    private Author author3;
    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    @ChangeSet(order = "000", id = "dropDB", author = "iarmush", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthor", author = "iarmush", runAlways = true)
    public void initAuthors(MongoAuthorRepository mongoAuthorRepository) {
        author1 = mongoAuthorRepository.save(new Author("a1"));
        author2 = mongoAuthorRepository.save(new Author("a2"));
        author3 = mongoAuthorRepository.save(new Author("a3"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "iarmush", runAlways = true)
    public void initGenres(MongoGenreRepository mongoGenreRepository) {
        genre1 = mongoGenreRepository.save(new Genre("g1"));
        genre2 = mongoGenreRepository.save(new Genre("g2"));
        genre3 = mongoGenreRepository.save(new Genre("g3"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "iarmush", runAlways = true)
    public void initBooks(MongoBookRepository mongoBookRepository) {
        var book1 = new Book();
        book1.setTitle("b1");
        book1.addAuthor(author1);
        book1.addGenre(genre1);
        book1.addComment(new Comment("c11"));
        book1.addComment(new Comment("c12"));
        mongoBookRepository.save(book1);

        var book2 = new Book();
        book2.setTitle("b2");
        book2.addAuthor(author2);
        book2.addGenre(genre2);
        book2.addComment(new Comment("c21"));
        book2.addComment(new Comment("c22"));
        mongoBookRepository.save(book2);

        var book3 = new Book();
        book3.setTitle("b3");
        book3.addAuthor(author3);
        book3.addGenre(genre3);
        book3.addComment(new Comment("c31"));
        book3.addComment(new Comment("c32"));
        mongoBookRepository.save(book3);
    }
}
