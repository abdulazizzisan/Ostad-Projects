package dev.zisan.bookapi.dto;

import dev.zisan.bookapi.entity.Book;
import org.springframework.stereotype.Component;

public class BookMapper {

    // entity to dto
    public static BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }

        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
//                .title("Hello World")
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publicationDate(book.getPublicationDate())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    // create request to entity
    public static Book toEntity(CreateBookRequest request) {
        if (request == null) {
            return null;
        }

        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .publicationDate(request.getPublicationDate())
                .build();
    }

    public static void updateEntity(Book book, UpdateBookRequest request) {
        if (request == null || book == null) {
            return;
        }

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }
        if (request.getPublicationDate() != null) {
            book.setPublicationDate(request.getPublicationDate());
        }
    }
}