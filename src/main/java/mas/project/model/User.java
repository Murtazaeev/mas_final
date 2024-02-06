package mas.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mas.project.expception.ModelValidationException;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Entity(name = "users")
@NoArgsConstructor(force = true)
public class User {

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

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @ToString.Exclude
    private Manager manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesman_id", referencedColumnName = "id")
    @ToString.Exclude
    private Salesman salesman;

    public void setCustomer(Customer customer) {
        if(getCustomer() != null){
            throw new ModelValidationException("Customer already set!");
        } else {
            this.customer = customer;
        }
    }

    public void setSalesman(Salesman salesman) {
        if(getSalesman() != null){
            throw new ModelValidationException("Salesman already set!");
        }
        if(getManager() != null){
            throw new ModelValidationException("Manager cannot be a salesman in the same time!");
        }
        this.salesman = salesman;
    }

    public void setManager(Manager manager) {
        if(getManager() != null){
            throw new ModelValidationException("Manager already set!");
        }
        if(getSalesman() != null){
            throw new ModelValidationException("Manager cannot be a salesman in the same time!");
        }
        this.manager = manager;
    }
}
