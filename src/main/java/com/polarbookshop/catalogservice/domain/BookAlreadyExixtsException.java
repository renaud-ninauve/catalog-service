package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExixtsException extends RuntimeException {
    public BookAlreadyExixtsException(String isbn) {
        super("book " + isbn + " already exists");
    }
}
