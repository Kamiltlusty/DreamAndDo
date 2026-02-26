package pl.kamil.dreamanddoapi.domain.ports.incoming;

import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

public interface AddDream {
    Dream save(Dream dream);
}
