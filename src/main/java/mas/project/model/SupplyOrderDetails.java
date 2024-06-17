package mas.project.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "supplyOrderDetails")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_item_id", "supply_order_id"})
})
public class SupplyOrderDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank
    private Date predictOrderExecutionDate;

    private String specialRequirements;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_item_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderItem orderItem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supply_order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SupplyOrder supplyOrder;
}
