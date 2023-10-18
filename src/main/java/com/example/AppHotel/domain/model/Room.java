package com.example.AppHotel.domain.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Room {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private int id;
    private int number;
    private String state;
    private Float price;
    private String description;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reservations> reservations;


    public Room() {
    }

    public Room(int number, String state, Float price, String description, Reservations reservations) {
        this.number = number;
        this.state = state;
        this.price = price;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
