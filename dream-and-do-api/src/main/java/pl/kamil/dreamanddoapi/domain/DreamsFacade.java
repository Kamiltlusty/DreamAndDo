package pl.kamil.dreamanddoapi.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.kamil.dreamanddoapi.domain.exceptions.DreamAlreadyExistsException;
import pl.kamil.dreamanddoapi.domain.exceptions.DreamNotFoundException;
import pl.kamil.dreamanddoapi.domain.exceptions.MissingDreamException;
import pl.kamil.dreamanddoapi.domain.ports.incoming.AddDream;
import pl.kamil.dreamanddoapi.domain.ports.incoming.DeleteDream;
import pl.kamil.dreamanddoapi.domain.ports.incoming.RetrieveDreams;
import pl.kamil.dreamanddoapi.domain.ports.incoming.UpdateDream;
import pl.kamil.dreamanddoapi.domain.ports.outgoing.DreamsRepository;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DreamsFacade implements RetrieveDreams, AddDream, DeleteDream, UpdateDream {
    private final DreamsRepository dr;

    @Override
    public List<Dream> findAll() {
        return dr.findAll();
    }

    @Override
    @Transactional
    public Dream save(Dream dream) {
        if (dream == null) {
            throw new MissingDreamException("Dream is null");
        }
        try {
            return dr.save(dream);
        } catch (DataIntegrityViolationException e) {
            throw new DreamAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String title) {
        dr.deleteByTitle(title)
                .orElseThrow(() -> new DreamNotFoundException(
                        "Dream with title " + title + " not found"));
    }

    @Override
    @Transactional
    public Dream updateDream(String title) {
        return null;
    }
}
