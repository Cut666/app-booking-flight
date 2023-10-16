package com.booking.View.ViewMain;

import java.io.IOException;
import java.util.Scanner;

public class MenuMain {
    public void DisplayMain() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("|-----------APP BOOKING FLIGHT----------|");
        System.out.println("|---------------------------------------|");
        System.out.println("|          1) Đăng nhập                 |");
        System.out.println("|          2) Đăng ký                   |");
        System.out.println("|          3) Thoát chương trình        |");
        System.out.println("-----------------------------------------");
        System.out.print("Nhập lựa chọn : ");
        String n = sc.nextLine();
        while (!n.equals("1")&&!n.equals("2")&&!n.equals("3")){
            System.out.println("Nhập lại lựa chọn :");
            n = sc.nextLine();
        }
        switch (n){
            case "1":
                SignIn sign = new SignIn();
                sign.signIn();
                break;
            case "2":
                Register register = new Register();
                register.register();
                break;
            case "3":
                System.out.println("      HẸN GẶP LẠI");
                System.exit(0);
                break;
        }
    }
}
