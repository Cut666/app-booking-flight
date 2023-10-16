package com.booking.View.ViewForAdmin;

import com.booking.controller.LogicForAdmin.LogicNotificationAndVoucher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class NotificationVoucherFlight {
    public void ViewNotificationVoucher() throws IOException {
        LogicNotificationAndVoucher logicNotificationAndVoucher = new LogicNotificationAndVoucher();
        System.out.println("----------------------------------");
        System.out.println("------ THÔNG BÁO VÀ VOUCHER ------");
        System.out.println("----------------------------------");
        System.out.println("-1) Gửi thông báo cho người dùng--");
        System.out.println("-2) Tạo mã Voucher Khuyến Mại   --");
        System.out.println("-3) Danh sách Voucher Khuyến Mại--");
        System.out.println("-4) Thoát                       --");
        System.out.println("----------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập lựa chọn: ");
        String n = sc.nextLine();
        while (!n.equals("1")&&!n.equals("2")&&!n.equals("3")&&!n.equals("4")){
            System.out.print("Nhập lại : ");
            n= sc.nextLine();
        }
        switch (n){
            case "1":
                System.out.println("Nhập email người nhận thông báo");
                String email = sc.nextLine();
                //email=sc.nextLine();
                logicNotificationAndVoucher.SendNotification(email);
                break;
            case "2":
                logicNotificationAndVoucher.AddVoucher();
                break;
            case "3":
                logicNotificationAndVoucher.ShowListVoucher();
                break;
            case "4":
                MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
                menuOptionAdmin.MenuOptionAdmin();
                break;
        }
    }
}
