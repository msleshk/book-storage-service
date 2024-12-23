package com.example.book_storage_service.service.implementation;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.exceptions.BookNotFoundException;
import com.example.book_storage_service.model.Book;
import com.example.book_storage_service.service.implementation.BookServiceImpl;
import com.example.book_storage_service.feign.BookTrackerFeignClient;
import com.example.book_storage_service.repository.BookRepository;
import com.example.book_storage_service.util.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Mapper<Book, BookDto> bookMapper;

    @Mock
    private BookTrackerFeignClient feignClient;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookDto bookDto;
    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookDto = new BookDto("Title", "Author", "Genre", "Description", "978-3-16-148411-0");
        book = new Book("Title", "Author", "Genre", "Description", "978-3-16-148411-0");
    }

    @Test
    void addBook_ShouldSaveBookAndNotify() {
        when(bookMapper.toEntity(any(BookDto.class))).thenReturn(book);  // Настроим поведение для toEntity
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        bookService.addBook(bookDto);

        verify(bookRepository, times(1)).save(book);
        verify(feignClient, times(1)).notifyBookCreated(book.getId());
    }

    @Test
    void getAllBooks_ShouldReturnBookList() {
        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(bookMapper.toDTO(any(Book.class))).thenReturn(bookDto);

        var result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bookDto, result.get(0));
    }

    @Test
    void getBookById_ShouldReturnBookDto_WhenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDTO(any(Book.class))).thenReturn(bookDto);

        var result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void getBookById_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        BookNotFoundException bookNotFoundException = assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
    }

    @Test
    void getBookByIsbn_ShouldReturnBookDto_WhenBookExists() {
        when(bookRepository.findBookByIsbn("978-3-16-148411-0")).thenReturn(Optional.of(book));
        when(bookMapper.toDTO(any(Book.class))).thenReturn(bookDto);  // Настроим поведение для toDTO

        var result = bookService.getBookByIsbn("978-3-16-148411-0");

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void getBookByIsbn_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findBookByIsbn("978-3-16-148411-0")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByIsbn("978-3-16-148411-0"));
    }

    @Test
    void deleteBookById_ShouldDeleteBookAndNotify() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBookById(1L);

        verify(bookRepository, times(1)).deleteById(1L);
        verify(feignClient, times(1)).notifyBookDeleted(1L);
    }


    @Test
    void updateBook_ShouldThrowException_WhenBookNotFound() {
        BookDto updatedBookDto = new BookDto("Updated Title", "Updated Author", "Updated Genre", "Updated Description", "978-3-16-148411-0");
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, updatedBookDto));
    }
}
