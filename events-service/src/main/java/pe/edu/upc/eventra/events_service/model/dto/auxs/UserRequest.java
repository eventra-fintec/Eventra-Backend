package pe.edu.upc.eventra.events_service.model.dto.auxs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long typeId;
}