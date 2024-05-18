package pe.edu.upc.eventra.reservations_service.service;

import pe.edu.upc.eventra.reservations_service.model.dto.ReservationRequest;
import pe.edu.upc.eventra.reservations_service.model.dto.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse addReservation(ReservationRequest reservationRequest);

    List<ReservationResponse> getAllReservations();

    ReservationResponse getReservationById(long id);

    ReservationResponse updateReservation(long id, ReservationRequest reservationRequest);

    void deleteReservation(long id);
}
