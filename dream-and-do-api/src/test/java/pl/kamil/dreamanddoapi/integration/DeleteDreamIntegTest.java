package pl.kamil.dreamanddoapi.integration;

import net.minidev.json.parser.JSONParser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.client.RestTestClient;
import pl.kamil.dreamanddoapi.TestcontainersInitializer;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;

import static pl.kamil.dreamanddoapi.TestcontainersInitializer.postgres;

@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestcontainersInitializer.class)
public class DeleteDreamIntegTest {
    private RestTestClient client;
    private JSONParser parser;

    @Autowired
    private DreamsRepository dr;

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
        parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
    }

    @Test
    void whenDreamDeletedSuccessfully_shouldReturn200() {
        // given, when, then
        client.delete()
                .uri(uriBuilder ->  uriBuilder
                        .path("/api/dreams/delete/{title}")
                        .build("Zrobić śniadanie"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void whenDreamNotFound_shouldThrowDreamNotFoundException() {
        // given
        // when, then
        client.delete()
                .uri(uriBuilder ->  uriBuilder
                        .path("/api/dreams/delete/{title}")
                        .build("Kupić hot-doga"))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
