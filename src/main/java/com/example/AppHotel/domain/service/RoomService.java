package com.example.AppHotel.domain.service;

import com.example.AppHotel.domain.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RoomService {
    Page<Room> getAllRoom(Pageable pageable);
    Room createRoom(Room room);
    Room updateRoom(int id, Room newRoom);
    ResponseEntity<?> deleteRoom(int id);
    Room getByRoomId(int id);
}
