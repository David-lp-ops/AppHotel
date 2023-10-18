package com.example.AppHotel.resource;

import com.example.AppHotel.domain.model.Room;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class SaveReservationResource {

    @NotBlank
    @Size(min =3, max =20, message="name client must have between 3 and 20 characters")
    private String nameClient;

    public String getNameClient(){
        return nameClient;
    }
    public SaveReservationResource setNameClient(String nameClient){
        this.nameClient =nameClient;
        return this;
    }
    @NotBlank
    private String dateReservation;
    public String getDateReservation(){
        return dateReservation;
    }
    public SaveReservationResource setDateReservation(String dateReservation){
        this.dateReservation =dateReservation;
        return this;
    }
    @NotBlank
    @Size(min =3, max =20, message="state  must have between 3 and 20 characters")
    private String state;
    public String getState(){
        return state;
    }
    public SaveReservationResource setState(String state){
        this.state =state;
        return this;
    }


}
