package pe.edu.upc.eventra.reservations_service.model.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequest {
    private Long userId;
    private Long ticketId;
    private Integer quantity;
    private LocalDateTime reservationDate;
}

