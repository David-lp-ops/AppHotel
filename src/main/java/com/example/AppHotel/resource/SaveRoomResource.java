package com.example.AppHotel.resource;

import com.example.AppHotel.domain.model.Reservations;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaveRoomResource {

    @NotNull
    private int number;
    @NotBlank
    @Size(min =3, max =20, message="state must have between 3 and 20 characters")
    private String state;
    @NotNull
    private Float price;
    @NotBlank

    private String description;


    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public SaveRoomResource setNumber(int number) {
        this.number = number;
        return this;
    }

    public SaveRoomResource setState(String state) {
        this.state = state;
        return this;
    }

    public SaveRoomResource setPrice(Float price) {
        this.price = price;
        return this;
    }

    public SaveRoomResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
