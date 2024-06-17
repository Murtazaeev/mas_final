package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "artwork_copy")
@Table(name = "artwork_copy")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ArtworkCopy {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    private int size;

    @NotNull
    private int quantity;

    @NotNull
    private double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artwork_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Artwork artwork;

    @OneToMany(mappedBy = "artworkCopy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderItem> orderItems = new HashSet<>();
}
