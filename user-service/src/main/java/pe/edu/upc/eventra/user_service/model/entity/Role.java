package pe.edu.upc.eventra.user_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private Roles role;

    public enum Roles {
        ROLE_USER,
        ROLE_ESTHUSIASTIC,
        ROLE_ORGANIZER
    }

    @Override
    public String toString() {
        return "TypeOfUser [typeId=" + id + ", description=" + role + "]";
    }
}
