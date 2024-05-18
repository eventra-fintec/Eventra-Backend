package pe.edu.upc.eventra.user_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.upc.eventra.sharedservice.model.AuditableModel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Role role;

    @Override
    public String toString() {
        return "User [userId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + "[PROTECTED], typeOfUser=" + role + "]";
    }
}
