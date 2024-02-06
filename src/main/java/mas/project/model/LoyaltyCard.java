package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Entity(name = "loyaltyCard")
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCard {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 80)
    private String cardName;

    @NotNull
    private Double priceReductionInPercentage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;


}
