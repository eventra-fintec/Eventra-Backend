package pe.edu.upc.eventra.user_service.service;

import pe.edu.upc.eventra.user_service.model.dto.UserRequest;
import pe.edu.upc.eventra.user_service.model.dto.UserResponse;

import java.util.List;

public interface UserService {

    void addUser(UserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(long id);

    void updateUser(long id, UserRequest userRequest);

    void deleteUser(long id);
}
