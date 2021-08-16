package ru.otus.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.lesson.domain.Author;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDto {
    private String id;
    private String name;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }

    public static Author toDomain(AuthorDto dto) {
        return new Author(dto.id, dto.name);
    }
}
