package ru.otus.lesson.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("books")
public class Book {

    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    @Field("authors")
    private Set<Author> authors = new HashSet<>();

    @DBRef
    @Field("genres")
    private Set<Genre> genres = new HashSet<>();

    @Field("comments")
    private List<Comment> comments = new ArrayList<>();

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Book{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", authors=" + authors +
            ", genres=" + genres +
            ", comments=" + comments +
            '}';
    }
}
