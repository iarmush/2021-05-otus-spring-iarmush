package ru.otus.lesson.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
