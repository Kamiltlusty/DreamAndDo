package pl.kamil.dreamanddoapi.infrastracture;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

public interface JpaDreamsRepository extends JpaRepository<Dream, Integer>, DreamsRepository {}
