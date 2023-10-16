package com.booking.model;

import java.util.Calendar;

public class Booking extends Flight {
    private String userEmail;
    private String voucherCode;
    private String codeBooking; //
    private String notification;

    public Booking() {
    }

    public Booking(String flightCode, String flightName, String fromPlace, String toPlace,
                   Calendar time, float price, int numberOfSeats,
                   String userEmail, String voucherCode, String codeBooking) {
        super(flightCode, flightName, fromPlace, toPlace, time, price, numberOfSeats);
        this.userEmail = userEmail;
        this.voucherCode = voucherCode;
        this.codeBooking = codeBooking;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "Mã đặt vé: " + codeBooking +
                "\n" + super.toString() +
                "\nSố ghế ngồi: " + super.getNumberOfSeats();
    }
}
