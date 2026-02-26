package pl.kamil.dreamanddoapi.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.testcontainers.postgresql.PostgreSQLContainer;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetrieveDreamsIntegTest {

    private RestTestClient client;
    static PostgreSQLContainer postgres;

    @LocalServerPort
    private Integer port;

    static {
        postgres = new PostgreSQLContainer("postgres:18.2-alpine")
                .withDatabaseName("integration-tests-db")
                .withUsername("postgres")
                .withPassword("postgres")
                .withInitScript("schema-${platform}.sql");
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        client = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    void shouldFindAllDreams() {
        // given
        // when
        List<Dream> actual = client.get()
                .uri("/api/getDreams")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Dream>>() {
                })
                .returnResult()
                .getResponseBody();
        // then
        List<Dream> expected = provideDreams2ITTest;
        assertEquals(expected, actual);
    }

    List<Dream> provideDreams2ITTest = List.of(
            Dream.builder()
                    .id(1L)
                    .title("Zrobić śniadanie")
                    .description("")
                    .build(),
            Dream.builder()
                    .id(2L)
                    .title("Odrobić lekcje")
                    .description("")
                    .build(),
            Dream.builder()
                    .id(3L)
                    .title("Przeczytać lekturę")
                    .description("")
                    .build(),
            Dream.builder()
                    .id(4L)
                    .title("Spakować walizkę")
                    .description("")
                    .build());
}
