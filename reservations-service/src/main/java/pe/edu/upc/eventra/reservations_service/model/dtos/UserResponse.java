package pe.edu.upc.eventra.reservations_service.model.dtos;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SimpleTypeOfUserResponse {
        private Long typeId;
        private String description;
    }
}
