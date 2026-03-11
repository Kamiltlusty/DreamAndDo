package pl.kamil.dreamanddoapi.domain.ports.outgoing;

import org.springframework.stereotype.Repository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;
import java.util.Optional;

@Repository
public interface DreamsRepository {
    List<Dream> findAll();
    Dream save(Dream dream);
    Optional<Dream> deleteByTitle(String title);
//    Optional<Dream>
}
