package ru.otus.lesson.domain;

public class Author {

    private final String fullName;
    private long id;

    public Author(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
            "id=" + id +
            ", fullName='" + fullName + '\'' +
            '}';
    }
}
