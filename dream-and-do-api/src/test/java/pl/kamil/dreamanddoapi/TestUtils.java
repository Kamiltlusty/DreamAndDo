package pl.kamil.dreamanddoapi;

import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;

import java.util.List;

public class TestUtils {
    public static List<Dream> provideDreams() {
        return List.of(Dream.builder()
                        .id(1L)
                        .title("Zrobić zakupy")
                        .description("")
                        .build(),
                Dream.builder()
                        .id(2L)
                        .title("Zrobić grę")
                        .description("")
                        .build(),
                Dream.builder()
                        .id(3L)
                        .title("Pospać")
                        .description("")
                        .build()
        );
    }
}
