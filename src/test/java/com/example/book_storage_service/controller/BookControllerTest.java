package com.example.book_storage_service.controller;


import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        bookDto = new BookDto("Title", "Author", "Genre", "Description", "978-3-16-148411-0");
    }

    @Test
    void getBookById_ShouldReturnBookDto() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDto);

        mockMvc.perform(get("/books/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookDto.getId()))
                .andExpect(jsonPath("$.title").value(bookDto.getTitle()))
                .andExpect(jsonPath("$.author").value(bookDto.getAuthor()));
    }

    @Test
    void getBookByIsbn_ShouldReturnBookDto() throws Exception {
        when(bookService.getBookByIsbn("978-3-16-148411-0")).thenReturn(bookDto);

        mockMvc.perform(get("/books/isbn/978-3-16-148411-0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookDto.getId()))
                .andExpect(jsonPath("$.title").value(bookDto.getTitle()))
                .andExpect(jsonPath("$.author").value(bookDto.getAuthor()));
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(bookDto));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(bookDto.getId()))
                .andExpect(jsonPath("$[0].title").value(bookDto.getTitle()))
                .andExpect(jsonPath("$[0].author").value(bookDto.getAuthor()));
    }

    @Test
    void addBook_ShouldReturnSuccessMessage() throws Exception {
        doNothing().when(bookService).addBook(any(BookDto.class));

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content("{ \"id\": 1, \"title\": \"Title\", \"author\": \"Author\", \"genre\": \"Genre\", \"description\": \"Description\", \"isbn\": \"978-3-16-148411-0\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book added successfully"));
    }


    @Test
    void updateBook_ShouldReturnSuccessMessage() throws Exception {
        doNothing().when(bookService).updateBook(1L, bookDto);

        mockMvc.perform(patch("/books/1")
                        .contentType("application/json")
                        .content("{ \"id\": 1, \"title\": \"Updated Title\", \"author\": \"Updated Author\", \"genre\": \"Updated Genre\", \"description\": \"Updated Description\", \"isbn\": \"978-3-16-148411-0\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated successfully!"));
    }

    @Test
    void deleteBook_ShouldReturnSuccessMessage() throws Exception {
        doNothing().when(bookService).deleteBookById(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully"));
    }

}

