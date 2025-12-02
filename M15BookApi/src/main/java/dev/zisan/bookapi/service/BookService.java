package dev.zisan.bookapi.service;

import dev.zisan.bookapi.dto.BookDto;
import dev.zisan.bookapi.dto.BookMapper;
import dev.zisan.bookapi.dto.CreateBookRequest;
import dev.zisan.bookapi.dto.UpdateBookRequest;
import dev.zisan.bookapi.entity.Book;
import dev.zisan.bookapi.exception.DuplicateResourceException;
import dev.zisan.bookapi.exception.ResourceNotFoundException;
import dev.zisan.bookapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookDto createBook(CreateBookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Book with ISBN " + request.getIsbn() + " already exists");
        }

        Book book = BookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDto(savedBook);
    }

    @Transactional(readOnly = true)
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(BookMapper::toDto);
    }

    @Transactional(readOnly = true)
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return BookMapper.toDto(book);
    }

    @Transactional(readOnly = true)
    public Page<BookDto> searchBooks(String title, String author, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitleAndAuthor(title, author, pageable);
        return books.map(BookMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<BookDto> searchBooks(String searchTerm, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitleOrAuthorContaining(searchTerm, pageable);
        return books.map(BookMapper::toDto);
    }

    public BookDto updateBook(Long id, UpdateBookRequest request) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        if (request.getIsbn() != null && !request.getIsbn().equals(existingBook.getIsbn())) {
            if (bookRepository.existsByIsbn(request.getIsbn())) {
                throw new DuplicateResourceException("Book with ISBN " + request.getIsbn() + " already exists");
            }
        }

        BookMapper.updateEntity(existingBook, request);
        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toDto(updatedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookDto> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookDto> searchBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        return books.stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }
}
