package pe.edu.upc.eventra.user_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "typeofuser")
public class TypeOfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    public enum Roles {
        ROLE_USER,
        ROLE_ESTHUSIASTIC,
        ROLE_ORGANIZER
    }

    @Override
    public String toString() {
        return "TypeOfUser [typeId=" + typeId + ", description=" + role + "]";
    }
}
