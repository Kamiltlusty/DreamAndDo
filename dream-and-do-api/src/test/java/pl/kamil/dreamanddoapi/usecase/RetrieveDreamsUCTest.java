package pl.kamil.dreamanddoapi.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.ports.incoming.RetrieveDreams;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.InMemoryDreamsRepositoryImpl;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

import static pl.kamil.dreamanddoapi.TestUtils.provideDreams;

public class RetrieveDreamsUCTest {
    RetrieveDreams dreamsService;
    DreamsRepository dr;

    @BeforeEach
    public void setup() {
        dr = new InMemoryDreamsRepositoryImpl();
        dreamsService = new DreamsFacade(dr);
    }

    @Test
    void shouldReturnDreamsList() {
        // given, when
        List<Dream> actual = dreamsService.findAll();
        // then
        List<Dream> expected = provideDreams();
        Assertions.assertEquals(expected, actual);
    }
}
