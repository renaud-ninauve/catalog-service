package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        BookValidationTest.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void succeed_when_all_fields_are_correct(){
        var book = new Book("1234567891", "toto", "tata", 12d);
        Set<ConstraintViolation<Book>> actual = validator.validate(book);
        assertThat(actual).isEmpty();
    }

    @Test
    void fail_when_isbn_incorrect(){
        var book = new Book("bad isbn", "toto", "tata", 12d);
        Set<ConstraintViolation<Book>> actual = validator.validate(book);
        assertThat(actual).hasSize(1);
        assertThat(actual.iterator().next().getMessage()).isEqualTo("The isbn format must be valid");
    }
}