package com.example.book_storage_service.mapper.implementation;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.model.Book;
import com.example.book_storage_service.util.implementation.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper();
    }

    @Test
    void toEntity_ShouldMapBookDtoToBook() {
        // Arrange
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setIsbn("978-3-16-148410-0");
        bookDto.setTitle("Title");
        bookDto.setGenre("Genre");
        bookDto.setDescription("Description");
        bookDto.setAuthor("Author");

        // Act
        Book book = bookMapper.toEntity(bookDto);

        // Assert
        assertEquals(bookDto.getId(), book.getId());
        assertEquals(bookDto.getIsbn(), book.getIsbn());
        assertEquals(bookDto.getTitle(), book.getTitle());
        assertEquals(bookDto.getGenre(), book.getGenre());
        assertEquals(bookDto.getDescription(), book.getDescription());
        assertEquals(bookDto.getAuthor(), book.getAuthor());
    }

    @Test
    void toDTO_ShouldMapBookToBookDto() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        book.setIsbn("978-3-16-148410-0");
        book.setTitle("Title");
        book.setGenre("Genre");
        book.setDescription("Description");
        book.setAuthor("Author");

        // Act
        BookDto bookDto = bookMapper.toDTO(book);

        // Assert
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getIsbn(), bookDto.getIsbn());
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getGenre(), bookDto.getGenre());
        assertEquals(book.getDescription(), bookDto.getDescription());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
    }
}

