package com.booking.model;

import java.util.Calendar;

public class Flight {

    private String flightCode;
    private String flightName;
    private String fromPlace;
    private String toPlace;
    private Calendar time;
    private float price;
    private int numberOfSeats;

    public Flight() {
    }

    public Flight(String flightCode, String flightName, String fromPlace, String toPlace,
                  Calendar time, float price, int numberOfSeats) {
        this.flightCode = flightCode;
        this.flightName = flightName;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.time = time;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Số hiệu chuyến bay: " + flightCode +
                "\nBay từ: " + fromPlace +
                "\nBay đến: " + toPlace +
                "\nThời gian: " + time.getTime() +
                "\nGiá: " + price;
    }
}
