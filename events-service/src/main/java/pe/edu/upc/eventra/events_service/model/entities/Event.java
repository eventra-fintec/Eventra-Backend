package pe.edu.upc.eventra.events_service.model.entities;

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
@Table(name = "events")
public class Event extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "id_organizer")
    private Long organizerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private CategoryEvent categoryEvent;
}

