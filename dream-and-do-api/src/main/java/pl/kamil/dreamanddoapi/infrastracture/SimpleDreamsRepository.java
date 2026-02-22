package pl.kamil.dreamanddoapi.infrastracture;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
public class SimpleDreamsRepository implements DreamsRepository {
    private final JpaDreamsRepository jpaDA;

    @Override
    public List<Dream> findAll() {
        return jpaDA.findAll();
    }
}
