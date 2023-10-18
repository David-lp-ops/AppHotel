package com.example.AppHotel.controller;

import com.example.AppHotel.domain.model.Reservations;
import com.example.AppHotel.domain.model.Room;
import com.example.AppHotel.domain.service.RoomService;
import com.example.AppHotel.resource.ReservationResource;
import com.example.AppHotel.resource.RoomResource;
import com.example.AppHotel.resource.SaveReservationResource;
import com.example.AppHotel.resource.SaveRoomResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RoomController
{
    @Autowired
    private RoomService roomService;
    @Autowired
    private ModelMapper mapper;

    private Room convertToEntity (SaveRoomResource resource){
        return  mapper.map(resource, Room.class);
    }

    private RoomResource converToResource (Room entity){
        return  mapper.map(entity, RoomResource.class);
    }

    @Operation(summary="Get all", description="Get all Room", tags = {"Room"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All Room returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/room")
    public Page<RoomResource> getAllRoom(Pageable pageable){
        List<RoomResource> room =roomService.getAllRoom(pageable)
                .getContent().stream().map(this::converToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(room, pageable, room.size());
    }
    @Operation(summary="Save Room", description="Save room", tags = {"room"} )
    @PostMapping("/room")
    public RoomResource createRoom (@Valid @RequestBody SaveRoomResource resource){
        return converToResource(roomService.createRoom(convertToEntity(resource)));
    }

    @Operation(summary="Update Room", description="Update room", tags = {"room"} )
    @PutMapping ("room/{id}")
    public RoomResource updateRoom (@PathVariable(name="id") int id, @Valid @RequestBody SaveRoomResource resource){
        return converToResource(roomService.updateRoom(id,convertToEntity(resource)));

    }

    @Operation(summary="Delete Room", description="Delete Room", tags = {"room"} )
    @DeleteMapping("room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable(name="id") int id){
        return roomService.deleteRoom(id);
    }

    @Operation(summary="Get by id", description="Get the Room giving an id", tags = {"room"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All room returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="room Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/room/{id}")
    public RoomResource getByRoomId(@PathVariable(name="id") int id){
        return converToResource(roomService.getByRoomId(id));
    }
}
