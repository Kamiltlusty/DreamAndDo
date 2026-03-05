package pl.kamil.dreamanddoapi.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.client.RestTestClient;
import pl.kamil.dreamanddoapi.TestcontainersInitializer;
import pl.kamil.dreamanddoapi.application.DreamDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.kamil.dreamanddoapi.TestcontainersInitializer.postgres;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestcontainersInitializer.class)
public class RetrieveDreamsIntegTest {
    private RestTestClient client;

    @LocalServerPort
    private Integer port;

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
        List<DreamDTO> actual = client.get()
                .uri("/api/dreams/getAll")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<DreamDTO>>() {
                })
                .returnResult()
                .getResponseBody();
        // then
        List<DreamDTO> expected = provideDreams2ITTest;
        assertEquals(expected, actual);
    }

    List<DreamDTO> provideDreams2ITTest = List.of(
            DreamDTO.builder()
                    .title("Zrobić śniadanie")
                    .description("")
                    .build(),
            DreamDTO.builder()
                    .title("Odrobić lekcje")
                    .description("")
                    .build(),
            DreamDTO.builder()
                    .title("Przeczytać lekturę")
                    .description("")
                    .build(),
            DreamDTO.builder()
                    .title("Spakować walizkę")
                    .description("")
                    .build());
}
