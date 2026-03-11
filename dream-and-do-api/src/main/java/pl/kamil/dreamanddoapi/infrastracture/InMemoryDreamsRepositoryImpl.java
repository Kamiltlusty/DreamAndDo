package pl.kamil.dreamanddoapi.infrastracture;

import org.springframework.stereotype.Component;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;
import java.util.Optional;

@Component
public class InMemoryDreamsRepositoryImpl implements DreamsRepository {
    @Override
    public List<Dream> findAll() {
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

    @Override
    public Dream save(Dream dream) {
        return null;
    }

    @Override
    public Optional<Dream> deleteByTitle(String title) {return Optional.of(Dream.builder().build());}
}
