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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BookServiceTestWithoutMockito {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    private Book book;
    private BookDto bookDto;
    private CreateBookRequest createBookRequest;

    @BeforeEach
    void setUp() {
        book = Book.builder()
//                .id(1L)
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

        bookRepository.deleteAll();
//        bookRepository.save(book);
    }

    @Test
    void createBook_ShouldReturnBookDtoWhenValidBookIsProvided() {

        BookDto createdBookDto = bookService.createBook(createBookRequest);

        assertNotNull(createdBookDto);
        assertEquals(bookDto.getTitle(), createdBookDto.getTitle());
        assertEquals(bookDto.getAuthor(), createdBookDto.getAuthor());

    }

    @Test
    void createBook_ShouldThrowDuplicateResourceExceptionWhenIsbnAlreadyExists(){
        bookRepository.save(book);

        assertThrows(
                DuplicateResourceException.class,
                () -> bookService.createBook(createBookRequest)
        );
    }




}