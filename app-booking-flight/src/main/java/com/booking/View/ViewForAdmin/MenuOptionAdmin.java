package com.booking.View.ViewForAdmin;

import com.booking.View.ViewMain.MenuMain;
import com.booking.controller.LogicForAdmin.LogicListFlight;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MenuOptionAdmin {
    public void MenuOptionAdmin() throws IOException {
        AddDeleteFlight addDeleteFlight = new AddDeleteFlight();
        LogicListFlight logicListFlight= new LogicListFlight();
        ManagementUser managementUser = new ManagementUser();
        NotificationVoucherFlight notificationVoucherFlight= new NotificationVoucherFlight();
        MenuMain menuMain = new MenuMain();
        System.out.println("---------------------------------------");
        System.out.println("-------------- MENU ADMIN -------------");
        System.out.println("---------------------------------------");
        System.out.println("--1) Thêm , hủy chuyến bay           --");
        System.out.println("--2) Sửa thông tin chuyến bay        --");
        System.out.println("--3) Quản lý người dùng              --");
        System.out.println("--4) Hiển thị list chuyến bay đã có  --");
        System.out.println("--5) Thông báo và voucher người dùng --");
        System.out.println("--6) Đăng xuất                       --");
        System.out.println("---------------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập lựa chọn: ");
        String n = sc.nextLine();
        while (!n.equals("1")&&!n.equals("2")&&!n.equals("3")&&!n.equals("4")&&!n.equals("5")&&!n.equals("6")){
            System.out.print("Nhập lại : ");
            n = sc.nextLine();
        }
        switch (n){
            case "1":
                addDeleteFlight.AddDeleteFlight();
                break;
            case "2":
                logicListFlight.ChangeFlight();
                break;
            case "3":
                managementUser.ViewManagement();
                break;
            case "4":
                logicListFlight.ShowListFlight();
                break;
            case "5":
                notificationVoucherFlight.ViewNotificationVoucher();
                break;
            case "6":
                menuMain.DisplayMain();
                break;
        }

    }
}
