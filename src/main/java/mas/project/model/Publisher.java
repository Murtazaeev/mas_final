package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "publisher")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Publisher {

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

    @NotNull
    private Address address;

    @NotBlank
    @Size(min = 6, max = 14)
    private String phone;

    @OneToMany(mappedBy = "publisher")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Book> books = new HashSet<>();


}
