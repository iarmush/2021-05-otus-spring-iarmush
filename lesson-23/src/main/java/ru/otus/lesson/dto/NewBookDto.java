package ru.otus.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewBookDto {

    private String id;
    private String title;
    private String authorFullName;
    private String genreName;
}
