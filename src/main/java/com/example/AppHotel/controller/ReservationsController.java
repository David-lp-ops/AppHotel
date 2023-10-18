package com.example.AppHotel.controller;

import com.example.AppHotel.domain.model.Reservations;
import com.example.AppHotel.domain.service.ReservationsService;
import com.example.AppHotel.resource.ReservationResource;
import com.example.AppHotel.resource.SaveReservationResource;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReservationsController {
    @Autowired
    private ReservationsService reservationsService;


    @Autowired
    private ModelMapper mapper;
    private Reservations convertToEntity(SaveReservationResource resource){
        return mapper.map(resource, Reservations.class);
    }
    private ReservationResource converToResource(Reservations entity){
        return  mapper.map(entity, ReservationResource.class);
    }

    @Operation(summary="Get all", description="Get all Reservation", tags = {"Reservation"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All Reservations returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reservations")
    public Page<ReservationResource> getAllReservations(Pageable pageable){
        List<ReservationResource> reservation =reservationsService.getAllReservations(pageable)
                .getContent().stream().map(this::converToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(reservation, pageable, reservation.size());
    }

    @Operation(summary="Save Reservations", description="Save reservations", tags = {"reservations"} )
    @PostMapping("/reservations")
    public ReservationResource createReservations (@Valid @RequestBody SaveReservationResource resource){
        return converToResource(reservationsService.createReservations(convertToEntity(resource)));
    }

    @Operation(summary="Update Reservations", description="Update Reservations", tags = {"reservations"} )
    @PutMapping ("reservations/{id}")
    public ReservationResource updateReservations(@PathVariable(name="id") int id, @Valid @RequestBody SaveReservationResource resource){
        return converToResource(reservationsService.updateReservations(id,convertToEntity(resource)));
    }
    @Operation(summary="Delete Reservations", description="Delete Reservations", tags = {"reservations"} )
    @DeleteMapping("reservations/{id}")
    public ResponseEntity<?> deleteReservations(@PathVariable(name="id") int id){
        return reservationsService.deleteReservations(id);
    }

    @Operation(summary="Get by id", description="Get the Reservations giving an id", tags = {"reservations"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All reservations returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="reservations Not Found", content = @Content(mediaType = "application/json"))

    })

    @PostMapping("/reservations/{id}/assign/{roomId}")
    public void assignRoomToReservation(@PathVariable int id, @PathVariable int roomId) {
        reservationsService.assignRoomToReservation(id, roomId);
    }
    @GetMapping("/reservations/{id}")
    public Reservations getReservationById(@PathVariable int id) {
        return reservationsService.getById(id);
    }


}
