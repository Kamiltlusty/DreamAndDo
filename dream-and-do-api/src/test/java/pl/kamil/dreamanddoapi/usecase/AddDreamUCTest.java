package pl.kamil.dreamanddoapi.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.exceptions.MissingDreamException;
import pl.kamil.dreamanddoapi.domain.ports.incoming.AddDream;
import pl.kamil.dreamanddoapi.domain.ports.incoming.RetrieveDreams;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddDreamUCTest {

    @Mock
    private DreamsRepository dr;

    @InjectMocks
    private DreamsFacade df;

    @Test
    void shouldAddDream() {
        // given
        Dream dream = Dream.builder()
                .title("Zwiedzić Amerykę")
                .description("Objechać stany: Illinois, Texas, Minnesota")
                .build();
        when(dr.save(any(Dream.class))).thenReturn(dream);
        // when
        Dream actual = df.save(dream);
        // then
        Dream expected = dream;
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void shouldThrowMissingDream() {
        // given, when, then
        MissingDreamException actual = Assertions.assertThrows(MissingDreamException.class,
                () -> df.save(null));

        String message = "Dream is null";
        assertThat(actual.getMessage()).isEqualTo(message);
    }
}