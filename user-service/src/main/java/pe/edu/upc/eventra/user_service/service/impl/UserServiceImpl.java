package pe.edu.upc.eventra.user_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upc.eventra.sharedservice.exception.ResourceNotFoundException;
import pe.edu.upc.eventra.user_service.model.dto.UserRequest;
import pe.edu.upc.eventra.user_service.model.dto.UserResponse;
import pe.edu.upc.eventra.user_service.model.entity.Role;
import pe.edu.upc.eventra.user_service.model.entity.User;
import pe.edu.upc.eventra.user_service.repository.RoleRepository;
import pe.edu.upc.eventra.user_service.repository.UserRepository;
import pe.edu.upc.eventra.user_service.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void addUser(UserRequest request) {
        Role userType = roleRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfUser not found with id: " + request.getTypeId()));
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(userType)
                .build();
        userRepository.save(user);
        log.info("User added: {}", user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToUserResponse(user);
    }

    public void updateUser(long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        Role userType = roleRepository.findById(userRequest.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfUser not found with id: " + userRequest.getTypeId()));

        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setRole(userType);

        userRepository.save(existingUser);
        log.info("Updated User: {}", existingUser);
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("Deleted User with id {}", id);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse.SimpleTypeOfUserResponse typeResponse = UserResponse.SimpleTypeOfUserResponse.builder()
                .typeId(user.getRole().getId())
                .role(user.getRole().getRole().name())
                .build();
        return UserResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .typeOfUser(typeResponse)
                .build();
    }
}
