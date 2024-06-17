package mas.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String surname;

    @NotBlank
    @Size(max = 30)
    private String nickname;

    @NotBlank
    @Size(max = 128)
    private String password;

    @Size(max = 1000)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesman_id", referencedColumnName = "id")
    private Salesman salesman;

    public void setCustomer(Customer customer) {
        if (this.customer != null) {
            throw new IllegalStateException("Customer already set!");
        } else {
            this.customer = customer;
        }
    }

    public void setSalesman(Salesman salesman) {
        if (this.salesman != null) {
            throw new IllegalStateException("Salesman already set!");
        }
        if (this.manager != null) {
            throw new IllegalStateException("Manager cannot be a salesman at the same time!");
        }
        this.salesman = salesman;
    }

    public void setManager(Manager manager) {
        if (this.manager != null) {
            throw new IllegalStateException("Manager already set!");
        }
        if (this.salesman != null) {
            throw new IllegalStateException("Manager cannot be a salesman at the same time!");
        }
        this.manager = manager;
    }
}
