package com.example.AppHotel.resource;

import com.example.AppHotel.domain.model.Reservations;

public class RoomResource {

    private int id;
    private int number;
    private String state;
    private Float price;
    private String description;




    public int getId() {
        return id;
    }

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

    public RoomResource setId(int id) {
        this.id = id;
        return this;
    }

    public RoomResource setNumber(int number) {
        this.number = number;
        return this;
    }

    public RoomResource setState(String state) {
        this.state = state;
        return this;
    }

    public RoomResource setPrice(Float price) {
        this.price = price;
        return this;
    }

    public RoomResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
