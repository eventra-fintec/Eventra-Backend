package pe.edu.upc.eventra.tickets_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.eventra.tickets_service.model.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

