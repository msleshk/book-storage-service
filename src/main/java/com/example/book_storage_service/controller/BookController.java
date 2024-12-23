package com.example.book_storage_service.controller;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/books")

@Tag(name = "Books API", description = "API for managing books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get book by ID", description = "Returns information about a book by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = BookDto.class))),
    })
    public BookDto getBookById(@PathVariable("id") Long bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Get book by ISBN", description = "Returns information about a book by its ISBN")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = BookDto.class))),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public BookDto getBookByIsbn(@PathVariable("isbn") String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping()
    @Operation(summary = "Get all books", description = "Returns a list of all available books")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of books", content = @Content(schema = @Schema(implementation = BookDto.class)))
    })
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update book information", description = "Updates book details by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book successfully updated"),
            @ApiResponse(responseCode = "400", description = "Validation error in the request")
    })
    public String updateBook(@PathVariable("id") Long bookId, @RequestBody @Valid BookDto bookDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }
        bookService.updateBook(bookId, bookDto);
        return "Book updated successfully!";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Deletes a book by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public String deleteBook(@PathVariable("id") Long bookId){
        bookService.deleteBookById(bookId);
        return "Book deleted successfully";
    }

    @PostMapping
    @Operation(summary = "Add new book", description = "Adding new book in database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book added successfully")
    })
    public String addBook(@RequestBody @Valid BookDto bookDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().toString());
        }
        bookService.addBook(bookDto);
        return "Book added successfully";
    }
}
