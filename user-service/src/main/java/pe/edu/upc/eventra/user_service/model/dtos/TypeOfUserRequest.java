package pe.edu.upc.eventra.user_service.model.dtos;

import lombok.*;
import pe.edu.upc.eventra.user_service.model.entities.TypeOfUser;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUserRequest {
    private TypeOfUser.Roles role;
}
