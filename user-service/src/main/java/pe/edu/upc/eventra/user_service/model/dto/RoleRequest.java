package pe.edu.upc.eventra.user_service.model.dto;

import lombok.*;
import pe.edu.upc.eventra.user_service.model.entity.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private Role.Roles role;
}
