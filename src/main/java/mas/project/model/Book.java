package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "book")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Book {

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
    private Date publishDate;

    @NotNull
    @Min(1)
    private int numberOfPages;

    @NotBlank
    private String category;

    @NotNull
    @Min(0)
    private Double price;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderItem orderItem;

    @OneToOne(mappedBy = "book", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Stock stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Publisher publisher;

    @ManyToMany(mappedBy = "books")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Author> authors = new HashSet<>();
}
