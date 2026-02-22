package pl.kamil.dreamanddoapi.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DreamController {
    private final DreamsFacade gds;

    @GetMapping("/getDreams")
    public ResponseEntity<List<Dream>> getDreams() {
        List<Dream> dreams = gds.findAll();
        if (dreams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dreams, HttpStatus.OK);
    }
}
