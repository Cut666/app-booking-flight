package com.booking;

import com.booking.View.ViewMain.MenuMain;
import com.sun.jdi.connect.spi.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookingFlightApplication {
    public static void main(String[] args) throws IOException {
        MenuMain main = new MenuMain();
        main.DisplayMain();
    }
}
