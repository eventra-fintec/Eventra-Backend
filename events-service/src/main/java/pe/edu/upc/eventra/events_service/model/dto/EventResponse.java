package pe.edu.upc.eventra.events_service.model.dto;

import lombok.*;
import pe.edu.upc.eventra.events_service.model.dto.auxs.UserResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private UserResponse organizer;
    private CategoryResponse categoryEvent;
}

