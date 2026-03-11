package pl.kamil.dreamanddoapi.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.exceptions.DreamNotFoundException;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteDreamUCTest {

    @Mock
    private DreamsRepository dr;

    @InjectMocks
    private DreamsFacade df;

    @Test
    void givenDreamTitle_shouldDeleteDream() {
        // given
        String title = "Zrobić śniadanie";
        when(dr.deleteByTitle(title))
                .thenReturn(Optional.of(Dream.builder()
                        .title(title)
                        .build()));
        // when
        df.delete(title);
        // then
        Mockito.verify(dr, times(1)).deleteByTitle(title);
    }

    @Test
    void givenNonExistingValue_shouldThrowDreamNotFoundException() {
        // given
        String title = "Zrobić hot-doga";
        when(dr.deleteByTitle(title))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(DreamNotFoundException.class, () -> df.delete(title));
    }
}
