package pe.edu.upc.eventra.user_service.model.dtos;

import lombok.*;
import pe.edu.upc.eventra.user_service.model.entities.TypeOfUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUserResponse {
    private Long typeId;
    private TypeOfUser.Roles role;
}
