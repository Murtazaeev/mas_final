package mas.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "manager")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(force = true)
public class Manager extends Employee {

    @NotBlank
    private String qualification;

    @OneToOne(mappedBy = "manager", optional = false)
    @ToString.Exclude
    private User user;
}
