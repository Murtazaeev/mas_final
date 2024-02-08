package mas.project.dto;


import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDetailsDTO {
    private UUID id;
    private String email;
    private String name;
    private String surname;
}
