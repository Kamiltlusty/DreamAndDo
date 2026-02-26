package pl.kamil.dreamanddoapi.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.dreamanddoapi.domain.exceptions.MissingDreamException;
import pl.kamil.dreamanddoapi.domain.ports.incoming.AddDream;
import pl.kamil.dreamanddoapi.domain.ports.incoming.RetrieveDreams;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DreamsFacade implements RetrieveDreams, AddDream {
    private final DreamsRepository dr;

    @Override
    public List<Dream> findAll() {
        return dr.findAll();
    }

    @Override
    public Dream save(Dream dream) {
        if (dream == null) {
            throw new MissingDreamException("Dream is null");
        }

        return dr.save(dream);
    }
}
