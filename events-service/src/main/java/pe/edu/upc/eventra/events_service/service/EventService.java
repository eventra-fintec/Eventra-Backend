package pe.edu.upc.eventra.events_service.service;

import pe.edu.upc.eventra.events_service.model.dto.EventRequest;
import pe.edu.upc.eventra.events_service.model.dto.EventResponse;

import java.util.List;

public interface EventService {

    EventResponse addEvent(EventRequest eventRequest);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(long id);

    EventResponse updateEvent(long id, EventRequest eventRequest);

    void deleteEvent(long id);
}