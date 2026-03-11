package pl.kamil.dreamanddoapi.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.exceptions.DreamAlreadyExistsException;
import pl.kamil.dreamanddoapi.domain.exceptions.DreamNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/dreams")
@RequiredArgsConstructor
public class DreamController {
    private final DreamsFacade df;
    private final DreamMapper dm;

    @GetMapping("/getAll")
    public ResponseEntity<List<DreamDTO>> getDreams() {
        List<DreamDTO> dreams = df.findAll().stream()
                .map(dm::toDreamDTO).toList();
        if (dreams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dreams, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDream(
            @RequestBody DreamDTO dreamDTO
    ) {
        try {
            df.save(dm.toDream(dreamDTO));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DreamAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<Void> deleteDream(
            @PathVariable String title
    ) {
        try {
            df.delete(title);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
