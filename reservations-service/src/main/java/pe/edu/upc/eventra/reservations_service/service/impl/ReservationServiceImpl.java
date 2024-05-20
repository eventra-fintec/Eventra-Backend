package pe.edu.upc.eventra.reservations_service.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.eventra.reservations_service.model.dto.ReservationRequest;
import pe.edu.upc.eventra.reservations_service.model.dto.ReservationResponse;
import pe.edu.upc.eventra.reservations_service.model.dto.TicketResponse;
import pe.edu.upc.eventra.reservations_service.model.dto.UserResponse;
import pe.edu.upc.eventra.reservations_service.model.entity.Reservation;
import pe.edu.upc.eventra.reservations_service.repository.ReservationRepository;
import pe.edu.upc.eventra.reservations_service.client.TicketClient;
import pe.edu.upc.eventra.reservations_service.client.UserClient;
import pe.edu.upc.eventra.reservations_service.service.ReservationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserClient userClient;
    private final TicketClient ticketClient;

    @Transactional
    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        // Validate the eventID by calling the Event service
        ticketClient.getTicketById(reservationRequest.getTicketId());
        userClient.getUserById(reservationRequest.getUserId());

        Reservation reservation = Reservation.builder()
                .userId(reservationRequest.getUserId())
                .ticketId(reservationRequest.getTicketId())
                .quantity(reservationRequest.getQuantity())
                .reservationDate(reservationRequest.getReservationDate())
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("Reservation added: {}", savedReservation);
        return mapToReservationResponse(savedReservation);
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::mapToReservationResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponse getReservationById(long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
        return mapToReservationResponse(reservation);
    }

    @Transactional
    public ReservationResponse updateReservation(long id, ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));

        ticketClient.getTicketById(reservationRequest.getTicketId());
        userClient.getUserById(reservationRequest.getUserId());

        reservation.setUserId(reservationRequest.getUserId());
        reservation.setTicketId(reservationRequest.getTicketId());
        reservation.setQuantity(reservationRequest.getQuantity());
        reservation.setReservationDate(reservationRequest.getReservationDate());

        Reservation updatedReservation = reservationRepository.save(reservation);
        log.info("Updated Reservation: {}", updatedReservation);
        return mapToReservationResponse(updatedReservation);
    }

    public void deleteReservation(long id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Reservation not found with id: " + id);
        }
        reservationRepository.deleteById(id);
        log.info("Deleted Reservation with id: {}", id);
    }

    private ReservationResponse mapToReservationResponse(Reservation reservation) {
        UserResponse userResponse;
        TicketResponse ticketResponse;

        try {
            userResponse = userClient.getUserById(reservation.getUserId());
        } catch (FeignException e) {
            log.error("Service is unavailable, unable to fetch details", e);
            userResponse = UserResponse.builder()
                    .userId(null)
                    .firstName(null)
                    .lastName(null)
                    .email(null)
                    .typeOfUser(null)
                    .build();
        }

        try {
            ticketResponse = ticketClient.getTicketById(reservation.getTicketId());
        } catch (FeignException e) {
            log.error("Service is unavailable, unable to fetch details", e);
            ticketResponse = TicketResponse.builder()
                    .ticketID(null)
                    .event(null)
                    .price(null)
                    .totalAvailable(null)
                    .category(null)
                    .description(null)
                    .build();
        }

        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .user(userResponse)
                .ticket(ticketResponse)
                .quantity(reservation.getQuantity())
                .reservationDate(reservation.getReservationDate())
                .build();
    }
}
