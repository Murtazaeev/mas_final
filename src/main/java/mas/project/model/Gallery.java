package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "gallery")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Gallery {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Address address;

    @NotBlank
    private String phone;

    @OneToMany(mappedBy = "gallery")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Artwork> artworks = new HashSet<>();
}
