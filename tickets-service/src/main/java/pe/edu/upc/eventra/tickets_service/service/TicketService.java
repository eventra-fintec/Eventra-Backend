package pe.edu.upc.eventra.tickets_service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.tickets_service.model.dtos.EventResponse;
import pe.edu.upc.eventra.tickets_service.model.dtos.TicketRequest;
import pe.edu.upc.eventra.tickets_service.model.dtos.TicketResponse;
import pe.edu.upc.eventra.tickets_service.model.entities.Ticket;
import pe.edu.upc.eventra.tickets_service.repository.EventClient;
import pe.edu.upc.eventra.tickets_service.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {
    private final TicketRepository ticketRepository;
    private final EventClient eventClient; // Feign client to interact with the Event microservice

    @Transactional
    public TicketResponse addTicket(TicketRequest ticketRequest) {
        // Validate the eventID by calling the Event service
        eventClient.getEventById(ticketRequest.getEventID());

        Ticket ticket = Ticket.builder()
                .eventID(ticketRequest.getEventID())
                .price(ticketRequest.getPrice())
                .totalAvailable(ticketRequest.getTotalAvailable())
                .category(ticketRequest.getCategory())
                .description(ticketRequest.getDescription())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("Ticket added: {}", savedTicket);
        return mapToTicketResponse(savedTicket);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::mapToTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        return mapToTicketResponse(ticket);
    }

    @Transactional
    public TicketResponse updateTicket(long id, TicketRequest ticketRequest) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));

        // Validate the eventID by calling the Event service
        eventClient.getEventById(ticketRequest.getEventID());

        ticket.setEventID(ticketRequest.getEventID());
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setTotalAvailable(ticketRequest.getTotalAvailable());
        ticket.setCategory(ticketRequest.getCategory());
        ticket.setDescription(ticketRequest.getDescription());

        Ticket updatedTicket = ticketRepository.save(ticket);
        log.info("Updated Ticket: {}", updatedTicket);
        return mapToTicketResponse(updatedTicket);
    }

    public void deleteTicket(long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found with id: " + id);
        }
        ticketRepository.deleteById(id);
        log.info("Deleted Ticket with id: {}", id);
    }

    private TicketResponse mapToTicketResponse(Ticket ticket) {
        EventResponse eventResponse;
        try {
            eventResponse = eventClient.getEventById(ticket.getEventID());
        } catch (FeignException e) {
            log.error("Event service is unavailable, unable to fetch event details", e);
            // If the event service is down, you can choose to return null or a default EventResponse
            eventResponse = null;
        }

        return TicketResponse.builder()
                .ticketID(ticket.getTicketID())
                .event(eventResponse) // Include the full EventResponse here
                .price(ticket.getPrice())
                .totalAvailable(ticket.getTotalAvailable())
                .category(ticket.getCategory())
                .description(ticket.getDescription())
                .build();
    }

}

