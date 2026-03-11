package pl.kamil.dreamanddoapi.integration.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import pl.kamil.dreamanddoapi.TestcontainersInitializer;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.kamil.dreamanddoapi.TestcontainersInitializer.postgres;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = TestcontainersInitializer.class)
public class JPADreamsRepositoryTest {
    @Autowired
    private DreamsRepository dr;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    public void shouldReturnDreamProperly() {
        // given
        String title = "Zrobić śniadanie";
        // when
        Optional<Dream> actual = dr.deleteByTitle(title);
        // then
        Dream expected = Dream.builder().title(title).build();
        assertEquals(expected.getTitle(), actual.get().getTitle());
    }

    @Test
    public void whenDreamWithGivenNameNotExist_shouldThrowDreamNotFoundException() {
        // given
        String title = "Dream that never dreamed";
        // when, then
        assertTrue(dr.deleteByTitle(title).isEmpty());
    }
}
