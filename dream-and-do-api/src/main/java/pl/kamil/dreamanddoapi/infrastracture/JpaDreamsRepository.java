package pl.kamil.dreamanddoapi.infrastracture;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.Optional;

@Primary
public interface JpaDreamsRepository extends JpaRepository<Dream, Integer>, DreamsRepository {
    Optional<Dream> deleteByTitle(String title);
}
