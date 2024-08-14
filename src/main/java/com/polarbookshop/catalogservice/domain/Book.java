package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "book")
public record Book(
        @Id
        Long id,
        @Version
        int version,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @NotBlank(message = "The book isbn must be defined")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The isbn format must be valid")
        String isbn,
        @NotBlank(message = "The book title must be defined")
        String title,
        @NotBlank(message = "The book author must be defined")
        String author,
        @NotNull(message = "The book price must be defined")
        @Positive(message = "The book price must be positive")
        Double price) {
    public static Book of(String isbn, String title, String author, Double price) {
        return new Book(null, 0, null, null, isbn, title, author, price);
    }
}
