package mas.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CustomerDetailsDTO {
    private UUID id;
    private String email;
    private String name;
    private String surname;
}
