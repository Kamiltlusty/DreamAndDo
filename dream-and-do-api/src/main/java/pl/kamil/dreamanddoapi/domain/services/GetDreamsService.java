package pl.kamil.dreamanddoapi.domain.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kamil.dreamanddoapi.domain.ports.incoming.RetrieveDreams;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDreamsService implements RetrieveDreams {
    private final DreamsRepository dr;

    @Override
    public List<Dream> findAll() {
        return dr.findAll();
    }
}
