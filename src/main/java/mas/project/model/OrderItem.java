package mas.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "orderItem")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"artwork_copy_id", "order_id"})
})
public class OrderItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    @Min(1)
    private int quantity;

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;

    @OneToMany(mappedBy = "orderItem")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SupplyOrderDetails> supplyOrderDetails = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "artwork_copy_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ArtworkCopy artworkCopy;
}
