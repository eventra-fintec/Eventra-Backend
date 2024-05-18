package pe.edu.upc.eventra.tickets_service.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.tickets_service.model.dto.EventResponse;
import pe.edu.upc.eventra.tickets_service.model.dto.TicketRequest;
import pe.edu.upc.eventra.tickets_service.model.dto.TicketResponse;
import pe.edu.upc.eventra.tickets_service.model.entity.Ticket;
import pe.edu.upc.eventra.tickets_service.client.EventClient;
import pe.edu.upc.eventra.tickets_service.repository.TicketRepository;
import pe.edu.upc.eventra.tickets_service.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventClient eventClient;

    @Transactional
    public TicketResponse addTicket(TicketRequest ticketRequest) {
        // Validate the eventID by calling the Event service
        eventClient.getEventById(ticketRequest.getEventID());

        Ticket ticket = Ticket.builder()
                .eventId(ticketRequest.getEventID())
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

        ticket.setEventId(ticketRequest.getEventID());
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
            eventResponse = eventClient.getEventById(ticket.getEventId());
        } catch (FeignException e) {
            log.error("Event service is unavailable, unable to fetch event details", e);
            eventResponse = EventResponse.builder()
                    .id(null)
                    .title(null)
                    .description(null)
                    .startDate(null)
                    .endDate(null)
                    .location(null)
                    .organizer(null)
                    .categoryEvent(null)
                    .build();
        }

        return TicketResponse.builder()
                .ticketID(ticket.getId())
                .event(eventResponse)
                .price(ticket.getPrice())
                .totalAvailable(ticket.getTotalAvailable())
                .category(ticket.getCategory())
                .description(ticket.getDescription())
                .build();
    }

}

