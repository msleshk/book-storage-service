package com.example.book_storage_service.controller;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable("id") Long bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/{isbn}")
    public BookDto getBookByIsbn(@PathVariable("isbn") String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping()
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") Long bookId, @RequestBody @Valid BookDto bookDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }
        bookService.updateBook(bookId, bookDto);
        return "Book updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long bookId){
        bookService.deleteBookById(bookId);
        return "Book deleted successfully";
    }

    @PostMapping
    public String addBook(@RequestBody @Valid BookDto bookDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }
        bookService.addBook(bookDto);
        return "Book added successfully";
    }
}
