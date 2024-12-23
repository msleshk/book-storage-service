package com.example.book_storage_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class BookDto {

    private Long id;
    @Pattern(regexp = "\\d{3}-\\d-\\d{2}-\\d{6}-\\d", message = "ISBN must follow the format 000-0-00-000000-0")
    @NotEmpty(message = "ISBN cannot be empty")
    private String isbn;


    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Genre cannot be empty")
    private String genre;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Author cannot be empty")
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
