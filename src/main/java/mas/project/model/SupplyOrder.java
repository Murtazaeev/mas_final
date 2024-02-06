package mas.project.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "supplyOrder")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SupplyOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    private Date orderDate;

    @OneToMany(mappedBy = "supplyOrder", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SupplyOrderDetails> supplyOrderDetails = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "salesman_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Salesman salesman;

}
