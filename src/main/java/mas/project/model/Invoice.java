package mas.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity(name = "invoice")
@NoArgsConstructor(force = true)
public class Invoice {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    @Min(0)
    @Max(100)
    private Double taxRateInPercentage;

    @Formula(value = "(SELECT SUM(a.price*oi.quantity) FROM order_item oi, artworkcopy a, orders o WHERE o.id = order_id AND oi.order_id = o.id AND oi.artworkCopy_id = a.id)")
    private Double nettoPrice;

    @Formula(value = "(SELECT SUM(a.price*oi.quantity)*tax_rate_in_percentage*0.01+SUM(a.price*oi.quantity) FROM order_item oi, artworkCopy a, orders o WHERE o.id = order_id AND oi.order_id = o.id AND oi.artworkCopy_id = a.id)")
    private Double bruttoPrice;

    @JsonBackReference
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;
}