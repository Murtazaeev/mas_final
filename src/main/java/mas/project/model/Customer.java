package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank
    private String PESEL;

    @NotNull
    private Address address;

    @NotBlank
    @Size(min = 3, max = 30)
    private String email;

    @OneToOne(mappedBy = "customer", optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LoyaltyCard> loyaltyCards = new HashSet<>();
}
