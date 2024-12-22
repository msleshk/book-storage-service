package com.example.book_storage_service.service;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.model.Book;

import java.util.List;

public interface BookService {
    public void addBook(BookDto bookDto);

    public List<BookDto> getAllBooks();

    public BookDto getBookById(long id);

    public BookDto getBookByIsbn(String isbn);

    public void deleteBookById(Long id);

    public void updateBook(long id, BookDto book);
}
