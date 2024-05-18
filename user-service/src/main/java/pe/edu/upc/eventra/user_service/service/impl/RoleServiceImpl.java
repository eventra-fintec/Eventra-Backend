package pe.edu.upc.eventra.user_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.sharedservice.exception.ResourceNotFoundException;
import pe.edu.upc.eventra.user_service.model.dto.RoleRequest;
import pe.edu.upc.eventra.user_service.model.dto.RoleResponse;
import pe.edu.upc.eventra.user_service.model.entity.Role;
import pe.edu.upc.eventra.user_service.repository.RoleRepository;
import pe.edu.upc.eventra.user_service.service.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public void addTypeOfUser(RoleRequest roleRequest) {
        var typeOfUser = Role.builder()
                .role(roleRequest.getRole())
                .build();

        roleRepository.save(typeOfUser);
        log.info("Type of User added: {}", typeOfUser);
    }

    public List<RoleResponse> getAllTypeOfUsers() {
        var typeOfUsers = roleRepository.findAll();
        return typeOfUsers.stream().map(this::mapToTypeOfUserResponse).toList();
    }

    public void updateTypeOfUser(long id, RoleRequest roleRequest) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User type not found with id " + id));
        existingRole.setRole(roleRequest.getRole());
        roleRepository.save(existingRole);
        log.info("Updated User Type: {}", existingRole);
    }

    public void deleteTypeOfUser(long id) {
        roleRepository.deleteById(id);
        log.info("Deleted User Type with id {}", id);
    }

    private RoleResponse mapToTypeOfUserResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getRole())
                .build();
    }
}

