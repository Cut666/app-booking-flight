package com.booking.View.ViewForAdmin;

import com.booking.controller.LogicForAdmin.LogicListFlight;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AddDeleteFlight {
    public void AddDeleteFlight() throws IOException {
        System.out.println("-------------------------------");
        System.out.println("----- THÊM HỦY CHUYẾN BAY -----");
        System.out.println("-------------------------------");
        System.out.println("--     1) Thêm chuyến bay    --");
        System.out.println("--     2) Hủy chuyến bay     --");
        System.out.println("--     3) Thoát              --");
        System.out.println("-------------------------------");
        Scanner sc = new Scanner(System.in);
        LogicListFlight logicListFlight= new LogicListFlight();
        MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
        System.out.print("Nhập lựa chọn : ");
        String n = sc.nextLine();
        while (!n.equals("1")&&!n.equals("2")&&!n.equals("3")){
            System.out.println("Nhập lại lựa chọn :");
            n = sc.nextLine();
        }
        switch (n){
            case "1":
                logicListFlight.AddFlight();
                break;
            case "2":
                logicListFlight.CancelFlight();
                break;
            case "3":
                menuOptionAdmin.MenuOptionAdmin();
                break;
        }
    }
}
