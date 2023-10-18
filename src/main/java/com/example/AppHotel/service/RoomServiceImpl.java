package com.example.AppHotel.service;


import com.example.AppHotel.domain.model.Room;
import com.example.AppHotel.domain.repository.RoomRepository;
import com.example.AppHotel.domain.service.RoomService;
import com.example.AppHotel.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Page<Room> getAllRoom(Pageable pageable){
        return roomRepository.findAll(pageable);
    }
    @Override
    public Room createRoom(Room room){
        return roomRepository.save(room);
    }
    @Override
    public Room updateRoom(int id, Room newRoom) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));

        existingRoom.setNumber(newRoom.getNumber());
        existingRoom.setDescription(newRoom.getDescription());
        existingRoom.setState(newRoom.getState());
        existingRoom.setPrice(newRoom.getPrice());
        existingRoom.setReservations(newRoom.getReservations());

        return roomRepository.save(existingRoom);
    }
    @Override
    public ResponseEntity<?> deleteRoom(int id){
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room","id",id));
        roomRepository.delete(room);
        return ResponseEntity.ok().build();
    }
    @Override
    public Room getByRoomId(int id){
        return roomRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Room", "id",id));
    }
}
