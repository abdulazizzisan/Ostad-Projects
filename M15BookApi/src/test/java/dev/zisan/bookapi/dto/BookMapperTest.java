package dev.zisan.bookapi.dto;

import dev.zisan.bookapi.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    void nullBookShouldReturnNullDto(){
        assertNull(BookMapper.toDto(null));
    }

    @Test
    void toDto_ShouldConvertBookToDto(){
        Book book = Book.builder()
                .id(1L)
                .title("Test Book")
                .isbn("1234567890")
                .author("Test Author")
                .publicationDate(LocalDate.of(2020,1,1))
                .createdAt(LocalDateTime.of(2010,1, 1, 10,0))
                .updatedAt(LocalDateTime.of(2010,1, 1, 10,0))
                .build();

        BookDto bookDto = BookMapper.toDto(book);

        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
        assertEquals(book.getIsbn(), bookDto.getIsbn());
        assertEquals(book.getPublicationDate(), bookDto.getPublicationDate());
        assertEquals(book.getCreatedAt(), bookDto.getCreatedAt());
        assertEquals(book.getUpdatedAt(), bookDto.getUpdatedAt());

    }

    @Test
    void toEntity_ShouldReturnNullForNullRequest() {
        assertNull(BookMapper.toEntity(null));
    }

    @Test
    void toEntity_ShouldConvertCreateBookRequestToEntity() {
        CreateBookRequest request = CreateBookRequest.builder()
                .title("Test Book")
                .author("Test Author")
                .isbn("1234567890")
                .publicationDate(LocalDate.of(2020, 1, 1))
                .build();

        Book book = BookMapper.toEntity(request);

        assertEquals(request.getTitle(), book.getTitle());
        assertEquals(request.getAuthor(), book.getAuthor());
        assertEquals(request.getIsbn(), book.getIsbn());
        assertEquals(request.getPublicationDate(), book.getPublicationDate());
    }

    @Test
    void updateEntity_ShouldWorkEvenIfAFieldIsNull(){
        Book book = Book.builder()
                .title("Original Title")
                .author("Original Author")
                .isbn("1111111111")
                .publicationDate(LocalDate.of(2010, 1, 1))
                .build();

        UpdateBookRequest request = UpdateBookRequest.builder()
                .title(null) // should not update
                .author("Updated Author")
                .isbn(null) // should not update
                .publicationDate(LocalDate.of(2022, 2, 2))
                .build();

        BookMapper.updateEntity(book, request);


        assertEquals("Original Title", book.getTitle()); // unchanged
        assertEquals("Updated Author", book.getAuthor()); // updated
        assertEquals("1111111111", book.getIsbn()); // unchanged
        assertEquals(LocalDate.of(2022, 2, 2), book.getPublicationDate()); // updated
    }

}