package pe.edu.upc.eventra.reservations_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.eventra.reservations_service.model.dto.TicketResponse;

@FeignClient(name = "tickets-service")
public interface TicketClient {
    @GetMapping("/api/tickets/{id}")
    TicketResponse getTicketById(@PathVariable("id") long id);
}
