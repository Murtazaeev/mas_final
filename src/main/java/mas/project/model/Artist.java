package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "artist")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 80)
    private String surname;

    @NotBlank
    @Size(max = 1000)
    private String biography;

    @NotNull
    private Date birthDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "artist_artwork",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "artwork_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Artwork> artworks = new HashSet<>();
}
