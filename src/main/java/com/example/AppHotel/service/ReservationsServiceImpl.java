package com.example.AppHotel.service;

import com.example.AppHotel.domain.model.Reservations;
import com.example.AppHotel.domain.model.Room;
import com.example.AppHotel.domain.repository.ReservationsRepository;
import com.example.AppHotel.domain.service.ReservationsService;
import com.example.AppHotel.domain.service.RoomService;
import com.example.AppHotel.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class ReservationsServiceImpl implements ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepository;
    @Autowired
    private RoomService roomService;
    @Override
    public Reservations getById(int id) {
        return reservationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservations", "id", id));
    }
    @Override
    public Page<Reservations> getAllReservations(Pageable pageable){
        return reservationsRepository.findAll(pageable);
    }
    @Override
    public Reservations createReservations( Reservations reservations){

        return reservationsRepository.save(reservations);
    }
    @Override
    public Reservations updateReservations(int id, Reservations newReservation) {
        Reservations reservations =  reservationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        reservations.setNameClient(newReservation.getNameClient());
        reservations.setDateReservation(newReservation.getDateReservation());
        reservations.setState(newReservation.getState());

        return reservationsRepository.save(reservations);
    }
    @Override
    public ResponseEntity<?> deleteReservations(int id){
        Reservations reservations = reservationsRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Reservations","id",id));
        reservationsRepository.delete(reservations);
        return ResponseEntity.ok().build();
    }
    @Override
    public Reservations getByReservationsId(int id){
        return reservationsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Reservations", "id",id));
    }
    @Override
    public void assignRoomToReservation(int id, int roomId) {
        Reservations reservation = getByReservationsId(id);
        Room room = roomService.getByRoomId(roomId);

        if (reservation != null && room != null) {
            reservation.setRoom(room);
            createReservations(reservation);
        }
    }
}
