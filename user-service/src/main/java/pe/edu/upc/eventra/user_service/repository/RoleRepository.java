package pe.edu.upc.eventra.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.user_service.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

