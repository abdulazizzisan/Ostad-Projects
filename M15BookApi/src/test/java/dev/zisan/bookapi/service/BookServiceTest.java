package dev.zisan.bookapi.service;

import dev.zisan.bookapi.dto.BookDto;
import dev.zisan.bookapi.dto.CreateBookRequest;
import dev.zisan.bookapi.entity.Book;
import dev.zisan.bookapi.exception.DuplicateResourceException;
import dev.zisan.bookapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    private Book book;
    private BookDto bookDto;
    private CreateBookRequest createBookRequest;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(1L)
                .title("Test Book")
                .author("Test Author")
                .isbn("1234567890")
                .build();

        bookDto = BookDto.builder()
                .id(1L)
                .title("Test Book")
                .author("Test Author")
                .isbn("1234567890")
                .build();

        createBookRequest = CreateBookRequest.builder()
                .title("Test Book")
                .author("Test Author")
                .isbn("1234567890")
                .build();
    }

    @Test
    void createBook_ShouldReturnBookDtoWhenValidBookIsProvided() {
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        BookDto createdBookDto = bookService.createBook(createBookRequest);

        assertNotNull(createdBookDto);
        assertEquals(bookDto.getTitle(), createdBookDto.getTitle());
        assertEquals(bookDto.getAuthor(), createdBookDto.getAuthor());

    }

    @Test
    void createBook_ShouldThrowDuplicateResourceExceptionWhenIsbnAlreadyExists(){
        when(bookRepository.existsByIsbn(anyString())).thenReturn(true);

        assertThrows(
                DuplicateResourceException.class,
                () -> bookService.createBook(createBookRequest)
        );
    }




}