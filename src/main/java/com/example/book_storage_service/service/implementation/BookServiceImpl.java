package com.example.book_storage_service.service.implementation;

import com.example.book_storage_service.dto.BookDto;
import com.example.book_storage_service.exceptions.BookNotFoundException;
import com.example.book_storage_service.feign.BookTrackerFeignClient;
import com.example.book_storage_service.model.Book;
import com.example.book_storage_service.repository.BookRepository;
import com.example.book_storage_service.service.BookService;
import com.example.book_storage_service.util.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Mapper<Book, BookDto> bookMapper;
    private final BookTrackerFeignClient feignClient;

    public BookServiceImpl(BookRepository bookRepository, @Qualifier("bookMapper") Mapper<Book, BookDto> bookMapper, BookTrackerFeignClient feignClient) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.feignClient = feignClient;
    }

    @Override
    @Transactional
    public void addBook(BookDto bookDto) {
        Book bookEntity = bookMapper.toEntity(bookDto);

        bookEntity = bookRepository.save(bookEntity);

        feignClient.notifyBookCreated(bookEntity.getId());
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream().map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(long id) {
        return bookMapper.toDTO(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No such book!")));
    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        return bookMapper.toDTO(bookRepository.findBookByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("No such book!")));
    }

    @Override
    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        feignClient.notifyBookDeleted(id);
    }

    @Override
    @Transactional
    public void updateBook(long id, BookDto updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No such book!"));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setIsbn(updatedBook.getIsbn());

        bookRepository.save(existingBook);
    }
}
