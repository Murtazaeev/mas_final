package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "artwork")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Artwork {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank
    private String title;

    @NotNull
    private Date createDate;

    @NotBlank
    private String category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gallery_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Gallery gallery;

    @ManyToMany(mappedBy = "artworks")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Artist> artists = new HashSet<>();
}
