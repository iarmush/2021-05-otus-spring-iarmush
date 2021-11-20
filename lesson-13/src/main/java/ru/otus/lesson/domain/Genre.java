package ru.otus.lesson.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("genres")
public class Genre {

    @Id
    private String id;

    @Field("name")
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
