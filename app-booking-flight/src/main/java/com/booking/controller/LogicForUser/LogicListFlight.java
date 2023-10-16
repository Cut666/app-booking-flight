package com.booking.controller.LogicForUser;

import com.booking.controller.LogicData.LogicFile;
import com.booking.controller.LogicData.LogicJson;
import com.booking.model.Flight;
import com.booking.model.Booking;
import com.booking.model.Voucher;
import static com.booking.View.ViewMain.SignIn.signedIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class LogicListFlight {
    LogicFile logicFile = new LogicFile();
    LogicJson logicJson = new LogicJson();
    List<Flight> flights;
    {
        try {
            flights = logicFile.ConvertFileToFlight();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // Convert file to list
    List<Voucher> vouchers;
    {
        try {
            vouchers = logicFile.ConvertFileToVoucher();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // Convert file to list
    List<Booking> bookings;
    {
        try {
            bookings = logicFile.ConvertFileToBooking();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // Convert file to list

    public ArrayList<Flight> SearchFlight(String fromPlace, String toPlace, String dateString) {
        Calendar dateCal = DateAnalysis.dateToCal(dateString);
        ArrayList<Flight> matchedFlights = new ArrayList<>();
        for (Flight temp: flights) {
            boolean b = temp.getFromPlace().equalsIgnoreCase(fromPlace)
                    && temp.getToPlace().equalsIgnoreCase(toPlace)
                    && temp.getTime().get(Calendar.YEAR) == dateCal.get(Calendar.YEAR)
                    && temp.getTime().get(Calendar.MONTH) == dateCal.get(Calendar.MONTH)
                    && temp.getTime().get(Calendar.DATE) == dateCal.get(Calendar.DATE)
                    && temp.getNumberOfSeats() > 0;
            if (b) matchedFlights.add(temp);
        }
        return matchedFlights;
    }
    public String generateBookingCode() {
        String randomised;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        while (true) {
            randomised = "";
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int n = random.nextInt(characters.length());
                randomised += characters.charAt(n);
            }
            int count = 0;
            for (Booking temp : bookings) {
                if (temp.getCodeBooking().equals(randomised)) {
                    count++;
                    break;
                }
            }
            if (count == 0) break;
        }
        return randomised;
    }
    public void selectFlight(Flight selFlight, int noSeats, Voucher selVoucher) {
        float percentage = 1-((float) selVoucher.getValueVoucher()/100);
        float discountedPrice = selFlight.getPrice()*noSeats*percentage;
        String bookingCode = generateBookingCode();
        Booking booked = new Booking(selFlight.getFlightCode(),selFlight.getFlightName(),
                selFlight.getFromPlace(),selFlight.getToPlace(),selFlight.getTime(),discountedPrice,
                noSeats,signedIn.getEmail(),selVoucher.getVoucherCode(),bookingCode);
        bookings.add(booked);
        logicFile.WriteStringJsonToFile(logicJson.ConvertObjectToStringJson(booked),"list_booking.txt");
        System.out.println("Đang đặt vé. 33% hoàn thành...");

        if (!selVoucher.getVoucherCode().equals("0")) {
            vouchers.remove(selVoucher);
            try {
                logicFile.DeleteVoucherInFile(vouchers);
            } catch (FileNotFoundException e) {
                System.out.println("Không tìm thấy file cần sửa.");
            }
        }
        System.out.println("Đang đặt vé. 66% hoàn thành...");
        // Xóa voucher nếu dùng

        for (Flight temp: flights)
            if (temp == selFlight) {
                int left = temp.getNumberOfSeats() - noSeats;
                temp.setNumberOfSeats(left);
                break;
            }
        try {
            logicFile.DeleteFlightInFile(flights);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file cần sửa.");
        }
        // Chỉnh sửa lại số ghế máy bay trong List và File
        System.out.println("Đang đặt vé. 100% hoàn thành...");
    }
    public Flight verifyFlight(ArrayList<Flight> filteredFlights, String flightCode) {
        for (Flight temp: filteredFlights)
            if (temp.getFlightCode().equals(flightCode.toUpperCase()))
                return temp;
        return null;
    }
    public Voucher verifyVoucher(String voucherCode) {
        for (Voucher temp: vouchers)
            if (temp.getVoucherCode().equals(voucherCode.toUpperCase()))
                return temp;
        return null;
    }
    public boolean cancelFlight(String bookingCode) {
        for (Booking booking: bookings)
            if (booking.getCodeBooking().equals(bookingCode.toUpperCase())) {
                for (Flight flight: flights)
                    if (booking.getFlightCode().toUpperCase().equals(flight.getFlightCode())) {
                        flights.remove(flight);
                        int newSeats = flight.getNumberOfSeats() + booking.getNumberOfSeats();
                        flight.setNumberOfSeats(newSeats);
                        flights.add(flight);
                        break;
                    }
                try {
                    logicFile.DeleteFlightInFile(flights);
                    // nếu xoá hết trong list -> file null -> tạo file trước khi out chương trình
                    File file = new File("list_flight.txt");
                    // if file  exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                } catch (IOException e) {
                    System.out.println("Đã xảy ra lỗi.");
                }

                bookings.remove(booking);
                try {
                    logicFile.DeleteBookingInFile(bookings);
                    // nếu xoá hết trong list -> file null -> tạo file trước khi out chương trình
                    File file = new File("list_booking.txt");
                    // if file  exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Không tìm thấy file.");
                } catch (IOException e) {
                    System.out.println("Đã xảy ra lỗi.");
                }
                return true;
            }
        return false;
    }
}
