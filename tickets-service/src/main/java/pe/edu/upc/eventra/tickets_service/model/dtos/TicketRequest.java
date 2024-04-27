package pe.edu.upc.eventra.tickets_service.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
    private Long eventID;
    private double price;
    private int totalAvailable;
    private String category;
    private String description;
}

