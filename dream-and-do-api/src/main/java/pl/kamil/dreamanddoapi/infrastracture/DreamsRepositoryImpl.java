package pl.kamil.dreamanddoapi.infrastracture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@Getter
@Primary
@Component
@RequiredArgsConstructor
public class DreamsRepositoryImpl implements DreamsRepository {
    private final JpaDreamsRepository jpaDA;

    @Override
    public List<Dream> findAll() {
        return jpaDA.findAll();
    }
}
