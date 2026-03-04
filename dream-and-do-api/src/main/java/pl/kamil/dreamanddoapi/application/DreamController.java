package pl.kamil.dreamanddoapi.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.exceptions.MissingDreamException;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@RestController
@RequestMapping("/api/dreams")
@RequiredArgsConstructor
public class DreamController {
    private final DreamsFacade gds;
    private final DreamMapper dm;

    @GetMapping("/getAll")
    public ResponseEntity<List<DreamDTO>> getDreams() {
        List<DreamDTO> dreams = gds.findAll().stream()
                .map(dm::toDreamDTO).toList();
        if (dreams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dreams, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DreamDTO> createDream(
            @RequestBody DreamDTO dreamDTO
    ) {
        try {
            Dream dream = dm.toDream(dreamDTO);
            DreamDTO dDTO = dm.toDreamDTO(gds.save(dream));
            return new ResponseEntity<>(dDTO, HttpStatus.CREATED);

        } catch (MissingDreamException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
