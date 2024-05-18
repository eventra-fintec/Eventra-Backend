package pe.edu.upc.eventra.user_service.model.dto;

import lombok.*;
import pe.edu.upc.eventra.user_service.model.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private Long id;
    private Role.Roles role;
}
