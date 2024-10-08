package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostThenBookIsCreated() {
        var expected = Book.of("1234567890", "beautiful title", "best author", 9.90d);

        webTestClient.post()
                .uri("/books")
                .bodyValue(expected)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actual -> {
                    assertThat(actual).isNotNull();
                    assertThat(actual.isbn()).isEqualTo(expected.isbn());
                });
    }
}