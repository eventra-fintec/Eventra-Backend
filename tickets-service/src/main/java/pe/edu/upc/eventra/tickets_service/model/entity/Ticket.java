package pe.edu.upc.eventra.tickets_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer totalAvailable;

    @Column
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;
}

