package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {
    @Autowired
    JacksonTester<Book> json;

    @Test
    void serialize() throws IOException {
        var book = new Book("1234567891", "a title", "an author", 9.90);
        var actual = json.write(book);
        assertThat(actual).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(actual).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(actual).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(actual).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void deserialize() throws IOException {
        var content = """
                {
                    "isbn": "1234567891",
                    "title": "a title",
                    "author": "an author",
                    "price": 9.90
                }
                """;
        ObjectContent<Book> actual = json.parse(content);
        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(new Book("1234567891", "a title", "an author", 9.90));
    }
}
