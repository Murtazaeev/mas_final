package mas.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import mas.project.model.enumeration.OrderState;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "orders")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotNull
    private Date orderDate;

    @NotNull
    private OrderState orderState;

    @Formula(value = "(SELECT SUM(b.price*oi.quantity) FROM order_item oi, book b WHERE oi.order_id = id AND oi.book_id = b.id)")
    private double retailPrice;

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @JsonBackReference
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderItem> orderItems = new HashSet<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "order", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Invoice invoice;

    //@Formula("(SELECT SUM(order_item.quantity*book.price) FROM order_item, book, orders WHERE order_item.order_id = orders.id AND book.id = order_item.book_id)")
    //private Double retailPrice;

    //  @Formula("(SELECT SUM(CAST(oi.quantity as DOUBLE PRECISION)* b.price) FROM order_item oi, book b WHERE oi.order_id = id AND oi.book_id = b.id)")
    // private double retailPrice;

    public boolean canGetInvoice(OrderState orderState){
        OrderState currentOrderState = getOrderState();
        if(currentOrderState == OrderState.WAITING_FOR_SUPPLY || currentOrderState == OrderState.CANCELED ){
            return false;
        }
        return true;
    }

}
