package pe.edu.upc.eventra.tickets_service.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;

    @Column(nullable = false)
    private Long eventID;

    @Column(nullable = false)
    private double price;

    @Column(name = "total_available", nullable = false)
    private int totalAvailable;

    @Column(length = 255)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;
}

