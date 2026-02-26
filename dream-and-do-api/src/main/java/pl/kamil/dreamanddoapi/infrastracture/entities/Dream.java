package pl.kamil.dreamanddoapi.infrastracture.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "dream")
@Builder
@ToString(of = {"id", "title"})
@EqualsAndHashCode(of = {"id", "title", "description"})
@NoArgsConstructor
@AllArgsConstructor
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
}

