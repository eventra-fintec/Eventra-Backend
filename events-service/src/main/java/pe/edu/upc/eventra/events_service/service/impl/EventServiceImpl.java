package pe.edu.upc.eventra.events_service.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.events_service.model.dto.CategoryResponse;
import pe.edu.upc.eventra.events_service.model.dto.EventRequest;
import pe.edu.upc.eventra.events_service.model.dto.EventResponse;
import pe.edu.upc.eventra.events_service.model.dto.auxs.UserResponse;
import pe.edu.upc.eventra.events_service.model.entity.Category;
import pe.edu.upc.eventra.events_service.model.entity.Event;
import pe.edu.upc.eventra.events_service.repository.CategoryRepository;
import pe.edu.upc.eventra.events_service.repository.EventRepository;
import pe.edu.upc.eventra.events_service.client.UserClient;
import pe.edu.upc.eventra.events_service.service.EventService;
import pe.edu.upc.eventra.sharedservice.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserClient userClient;

    @Transactional
    public EventResponse addEvent(EventRequest eventRequest) {
        UserResponse organizer = userClient.getUserById(eventRequest.getOrganizerId());

        Category category = categoryRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryEvent not found with id: " + eventRequest.getCategoryId()));

        Event event = Event.builder()
                .title(eventRequest.getTitle())
                .description(eventRequest.getDescription())
                .startDate(eventRequest.getStartDate())
                .endDate(eventRequest.getEndDate())
                .location(eventRequest.getLocation())
                .organizerId(organizer.getUserId())
                .category(category)
                .build();

        Event savedEvent = eventRepository.save(event);
        log.info("Event added: {}", savedEvent);
        return mapToEventResponse(savedEvent);
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
    }

    public EventResponse getEventById(long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        return mapToEventResponse(event);
    }

    @Transactional
    public EventResponse updateEvent(long id, EventRequest eventRequest) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        // Validate the organizerId by calling the User service
        userClient.getUserById(eventRequest.getOrganizerId());
        Category category = categoryRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryEvent not found with id: " + eventRequest.getCategoryId()));

        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        event.setLocation(eventRequest.getLocation());
        event.setOrganizerId(eventRequest.getOrganizerId()); // Set the validated user ID
        event.setCategory(category);

        Event updatedEvent = eventRepository.save(event);
        log.info("Updated Event: {}", updatedEvent);
        return mapToEventResponse(updatedEvent);
    }

    public void deleteEvent(long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
        log.info("Deleted Event with id: {}", id);
    }

    private EventResponse mapToEventResponse(Event event) {
        UserResponse organizer;
        try {
            organizer = userClient.getUserById(event.getOrganizerId());
        } catch (FeignException e) {
            log.error("User service is unavailable, unable to fetch organizer details", e);
            organizer = UserResponse.builder()
                    .userId(null)
                    .firstName(null)
                    .lastName(null)
                    .email(null)
                    .typeOfUser(null)
                    .build();
        }
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(event.getCategory().getId())
                .name(event.getCategory().getCategory())
                .build();

        // Now include UserResponse in the EventResponse
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .organizer(organizer)
                .categoryEvent(categoryResponse)
                .build();
    }
}

