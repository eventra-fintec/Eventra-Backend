package pe.edu.upc.eventra.events_service.model.entity;

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

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private String location;

    @Column
    private Long organizerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Category category;
}

