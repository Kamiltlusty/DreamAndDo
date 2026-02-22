package pl.kamil.dreamanddoapi.domain.ports.outgoing;

import org.springframework.stereotype.Repository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@Repository
public interface DreamsRepository {
    List<Dream> findAll();
}
