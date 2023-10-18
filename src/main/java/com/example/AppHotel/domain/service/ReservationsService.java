package com.example.AppHotel.domain.service;

import com.example.AppHotel.domain.model.Reservations;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;


public interface ReservationsService {
    Reservations getById(int id);
    Page<Reservations> getAllReservations(Pageable pageable);
    Reservations createReservations(Reservations reservations);
    Reservations updateReservations(int id, Reservations newReservation);
    ResponseEntity<?> deleteReservations(int id);
    void assignRoomToReservation(int id, int roomId);


    Reservations getByReservationsId(int id);
}
