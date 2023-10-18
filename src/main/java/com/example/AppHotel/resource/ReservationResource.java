package com.example.AppHotel.resource;


import com.example.AppHotel.domain.model.Room;

public class ReservationResource {
    private int id;
    private String nameClient;
    private String dateReservation;
    private String state;


    public int getId() {
        return id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public String getState() {
        return state;
    }

    public ReservationResource setId(int id){
        this.id=id;
        return this;
    }
    public ReservationResource setNameClient(String nameClient){
        this.nameClient=nameClient;
        return this;
    }
    public ReservationResource setDateReservation(String dateReservation){
        this.dateReservation=dateReservation;
        return this;
    }
    public ReservationResource setState(String state){
        this.state=state;
        return this;
    }
}
