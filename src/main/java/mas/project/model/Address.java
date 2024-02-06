package mas.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @NotBlank
    @Size(max = 50)
    private String street;

    @NotBlank
    @Size(max = 10)
    private String buildingNumber;

    @NotBlank
    @Size(max = 50)
    private String city;

}
