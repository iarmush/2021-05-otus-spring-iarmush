package ru.otus.lesson.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("authors")
public class Author {

    @Id
    private String id;

    @Field("full_name")
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }
}
