package pe.edu.upc.eventra.reservations_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.upc.eventra.sharedservice.model.AuditableModel;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reservations")
public class Reservation extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // User ID from another microservice

    @Column(nullable = false)
    private Long ticketId; // Ticket ID from another microservice

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime reservationDate;
}

