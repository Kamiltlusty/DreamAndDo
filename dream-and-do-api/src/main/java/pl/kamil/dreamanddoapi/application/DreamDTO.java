package pl.kamil.dreamanddoapi.application;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@ToString(of = {"title", "description"})
@EqualsAndHashCode(of = {"title", "description"})
@NoArgsConstructor
@AllArgsConstructor
public class DreamDTO {
    @NotBlank String title;
    @Nullable String description;
}
