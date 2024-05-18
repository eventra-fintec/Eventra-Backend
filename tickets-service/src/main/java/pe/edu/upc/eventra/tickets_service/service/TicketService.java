package pe.edu.upc.eventra.tickets_service.service;

import pe.edu.upc.eventra.tickets_service.model.dto.TicketRequest;
import pe.edu.upc.eventra.tickets_service.model.dto.TicketResponse;

import java.util.List;

public interface TicketService {

    TicketResponse addTicket(TicketRequest ticketRequest);

    List<TicketResponse> getAllTickets();

    TicketResponse getTicketById(long id);

    TicketResponse updateTicket(long id, TicketRequest ticketRequest);

    void deleteTicket(long id);
}
