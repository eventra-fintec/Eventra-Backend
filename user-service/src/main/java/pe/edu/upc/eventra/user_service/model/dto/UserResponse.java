package pe.edu.upc.eventra.user_service.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private SimpleTypeOfUserResponse typeOfUser;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SimpleTypeOfUserResponse {
        private Long typeId;
        private String role;
    }
}
