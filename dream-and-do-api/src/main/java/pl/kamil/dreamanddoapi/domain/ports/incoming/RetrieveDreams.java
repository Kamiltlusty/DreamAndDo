package pl.kamil.dreamanddoapi.domain.ports.incoming;

import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

public interface RetrieveDreams {
    List<Dream> findAll();
}
