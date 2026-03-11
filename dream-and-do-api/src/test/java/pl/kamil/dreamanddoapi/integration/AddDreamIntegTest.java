package pl.kamil.dreamanddoapi.integration;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.client.RestTestClient;
import pl.kamil.dreamanddoapi.TestcontainersInitializer;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;

import static pl.kamil.dreamanddoapi.TestcontainersInitializer.postgres;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = TestcontainersInitializer.class)
public class AddDreamIntegTest {
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
    void shouldAddDream() throws ParseException {
        // given
        String path = "/api/dreams/create";
        String bodyJSON = """
                {
                  "title":"Test Dream",
                  "description":"Test Dream description"
                }
                """;
        // when, then
        client.post()
                .uri(path)
                .body(parser.parse(bodyJSON))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Void.class);
    }

    @Test
    void shouldThrowConflict_WhenDreamViolatesUniqueConstraintOnTitle() throws ParseException {
        // given
        String path = "/api/dreams/create";
        String bodyJSON = """
                {
                  "title":"Zrobić śniadanie",
                  "description":""
                }
                """;
        // when, then
        client.post()
                .uri(path)
                .body(parser.parse(bodyJSON))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT)
                .expectBody(Void.class);

    }
}
