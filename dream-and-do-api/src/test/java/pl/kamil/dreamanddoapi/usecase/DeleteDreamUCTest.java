package pl.kamil.dreamanddoapi.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

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
        doNothing().when(dr)
                .deleteByTitle(title);
        // when
        df.delete(title);
        // then
        Mockito.verify(dr, times(1)).deleteByTitle(title);
    }

//    @Test
//    void givenNonExistingValue_shouldThrow
}
