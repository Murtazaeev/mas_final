package mas.project.model;

import com.sun.istack.NotNull;
import lombok.*;
import mas.project.model.enumeration.WorkingDays;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "salesman")
@NoArgsConstructor
@AllArgsConstructor
public class Salesman extends Employee {

    @NotNull
    private WorkingDays workingDays;

    @OneToOne(mappedBy = "salesman", optional = false)
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "salesman")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SupplyOrder> supplyOrders = new HashSet<>();
}