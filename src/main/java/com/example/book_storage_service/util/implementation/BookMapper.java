package com.example.book_storage_service.util.implementation;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.model.Book;
import com.example.book_storage_service.util.Mapper;
import org.springframework.stereotype.Component;

@Component("bookMapper")
public class BookMapper implements Mapper<Book, BookDto> {
    @Override
    public Book toEntity(BookDto dto) {
        Book book = new Book();

        book.setId(dto.getId());
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setGenre(dto.getGenre());
        book.setDescription(dto.getDescription());
        book.setAuthor(dto.getAuthor());

        return book;
    }

    @Override
    public BookDto toDTO(Book o) {
        BookDto dto = new BookDto();

        dto.setId(o.getId());
        dto.setIsbn(o.getIsbn());
        dto.setTitle(o.getTitle());
        dto.setGenre(o.getGenre());
        dto.setDescription(o.getDescription());
        dto.setAuthor(o.getAuthor());

        return dto;
    }
}
