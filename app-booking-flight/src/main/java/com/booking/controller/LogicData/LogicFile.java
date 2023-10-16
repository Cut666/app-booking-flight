package com.booking.controller.LogicData;

import com.booking.model.Booking;
import com.booking.model.Flight;
import com.booking.model.User;
import com.booking.model.Voucher;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogicFile {

    // viết 1 chuỗi json ra file
    public void WriteStringJsonToFile(String json,String url){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            File file = new File(url);
            // if file  exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(json+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //đọc file chuyển sang list objecct

    //list user
    public List<User> ConvertFileToUser() throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        Gson gson = new Gson();
        // Đọc dữ liệu từ File với File và FileReader
        File file = new File("list_user.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file)); 
        try {
            String json = reader.readLine();
            while (json != null) {
                User user = gson.fromJson(json, User.class);
                users.add(user);
                json = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                reader.close();
                // file.close();
            } catch (IOException ex) {
            }
        }
        return users;
    }

    //list flight
    public List<Flight> ConvertFileToFlight() throws FileNotFoundException {
        List<Flight> flights = new ArrayList<>();
        Gson gson = new Gson();
        // Đọc dữ liệu từ File với File và FileReader
        File file = new File("list_flight.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String json = reader.readLine();
            while (json != null) {
                Flight flight = gson.fromJson(json, Flight.class);
                //với những dòng trống khí sửa hoặc xoá thì next qua dòng tieép để đọc
                if(json!=null){
                    flights.add(flight);
                    json = reader.readLine();
                }
                else {
                    json = reader.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                reader.close();
                // file.close();
            } catch (IOException ex) {
            }
        }
        return flights;
    }

    //list voucher
    public List<Voucher> ConvertFileToVoucher() throws FileNotFoundException {
        List<Voucher> vouchers = new ArrayList<>();
        Gson gson = new Gson();
        // Đọc dữ liệu từ File với File và FileReader
        File file = new File("list_voucher.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String json = reader.readLine();
            while (json != null) {
                Voucher voucher = gson.fromJson(json, Voucher.class);
                //với những dòng trống khí sửa hoặc xoá thì next qua dòng tieép để đọc
                if(json!=null){
                    vouchers.add(voucher);
                    json = reader.readLine();
                }
                else {
                    json = reader.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                reader.close();
                // file.close();
            } catch (IOException ex) {
            }
        }
        return vouchers;
    }

    //list booking
    public List<Booking> ConvertFileToBooking() throws FileNotFoundException {
        List<Booking> bookings = new ArrayList<>();
        Gson gson = new Gson();
        // Đọc dữ liệu từ File với File và FileReader
        File file = new File("list_booking.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String json = reader.readLine();
            while (json != null) {
                Booking booking = gson.fromJson(json, Booking.class);
                //với những dòng trống khí sửa hoặc xoá thì next qua dòng tieép để đọc
                if(json!=null){
                    bookings.add(booking);
                    json = reader.readLine();
                }
                else {
                    json = reader.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                reader.close();
                // file.close();
            } catch (IOException ex) {
            }
        }
        return bookings;
    }


    //xoá file cũ và add file mới thông qua list

    public void DeleteFlightInFile(List<Flight> flights) throws FileNotFoundException {
        //xoá file cũ
        try{
            //Specify the file name and path
            File file = new File("list_flight.txt");

            if(file.delete()){
                //System.out.println(file.getName() + " is deleted!");
            }else{
                //System.out.println("Delete failed: File didn't delete");
            }
        }catch(Exception e){
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
        //lấy list mới đổi convert ra dãy mảng chuỗi rồi viết ra file cùng tên file vừa xoá
        LogicJson logicJson = new LogicJson();
        for (Flight flight : flights) {
            String json = logicJson.ConvertObjectToStringJson(flight);
            WriteStringJsonToFile(json,"list_flight.txt");
        }
    }

    public void DeleteBookingInFile(List<Booking> bookings) throws FileNotFoundException {
        //xoá file cũ
        try{
            //Specify the file name and path
            File file = new File("list_booking.txt");

            if(file.delete()){
                //System.out.println(file.getName() + " is deleted!");
            }else{
                //System.out.println("Delete failed: File didn't delete");
            }
        }catch(Exception e){
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
        //lấy list mới đổi convert ra dãy mảng chuỗi rồi viết ra file cùng tên file vừa xoá
        LogicJson logicJson = new LogicJson();
        for (Booking booking : bookings) {
            String json = logicJson.ConvertObjectToStringJson(booking);
            WriteStringJsonToFile(json,"list_booking.txt");
        }
    }

    public void DeleteVoucherInFile(List<Voucher> vouchers) throws FileNotFoundException {
        //xoá file cũ
        try{
            //Specify the file name and path
            File file = new File("list_voucher.txt");

            if(file.delete()){
                //System.out.println(file.getName() + " is deleted!");
            }else{
                //System.out.println("Delete failed: File didn't delete");
            }
        }catch(Exception e){
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
        //lấy list mới đổi convert ra dãy mảng chuỗi rồi viết ra file cùng tên file vừa xoá
        LogicJson logicJson = new LogicJson();
        for (Voucher voucher : vouchers) {
            String json = logicJson.ConvertObjectToStringJson(voucher);
            WriteStringJsonToFile(json,"list_voucher.txt");
        }
    }

    public void DeleteUserInFile(List<User> users) throws FileNotFoundException {
        //xoá file cũ
        try{
            //Specify the file name and path
            File file = new File("list_user.txt");

            if(file.delete()){
                //System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete failed: File didn't delete");
            }
        }catch(Exception e){
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
        //lấy list mới đổi convert ra dãy mảng chuỗi rồi viết ra file cùng tên file vừa xoá
        LogicJson logicJson = new LogicJson();
        for (User user : users) {
            String json = logicJson.ConvertObjectToStringJson(user);
            WriteStringJsonToFile(json,"list_user.txt");
        }
    }

}
