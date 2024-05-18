package pe.edu.upc.eventra.user_service.service;

import pe.edu.upc.eventra.user_service.model.dto.RoleRequest;
import pe.edu.upc.eventra.user_service.model.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    void addTypeOfUser(RoleRequest roleRequest);

    List<RoleResponse> getAllTypeOfUsers();

    void updateTypeOfUser(long id, RoleRequest roleRequest);

    void deleteTypeOfUser(long id);
}
