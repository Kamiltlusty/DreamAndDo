package pl.kamil.dreamanddoapi.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kamil.dreamanddoapi.domain.ports.incoming.RetrieveDreams;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.domain.services.GetDreamsService;
import pl.kamil.dreamanddoapi.infrastracture.InMemoryDreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

public class GetDreamUCTest {
    RetrieveDreams dreamsService;
    DreamsRepository dr;

    @BeforeEach
    public void setup() {
        dr = new InMemoryDreamsRepository();
        dreamsService = new GetDreamsService(dr);
    }

    @Test
    void shouldReturnDreamsList() {
        // given, when
        List<Dream> actual = dreamsService.findAll();
        // then
        List<Dream> expected = provideDreams();
        Assertions.assertEquals(expected, actual);
    }

    static List<Dream> provideDreams() {
        return List.of(Dream.builder()
                        .id(1L)
                        .title("Zrobić zakupy")
                        .description("")
                        .build(),
                Dream.builder()
                        .id(2L)
                        .title("Zrobić grę")
                        .description("")
                        .build(),
                Dream.builder()
                        .id(3L)
                        .title("Pospać")
                        .description("")
                        .build()
        );
    }
}
