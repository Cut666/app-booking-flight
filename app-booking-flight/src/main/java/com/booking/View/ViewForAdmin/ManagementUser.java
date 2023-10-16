package com.booking.View.ViewForAdmin;

import com.booking.controller.LogicForAdmin.LogicListUser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Scanner;

public class ManagementUser {
     public void ViewManagement() throws IOException {
         LogicListUser logicListUser = new LogicListUser();
         System.out.println("-----------------------------");
         System.out.println("---------QUẢN LÝ USER--------");
         System.out.println("-----------------------------");
         System.out.println("--  1) Danh sách list user --");
         System.out.println("--  2) Xoá người dùng      --");
         System.out.println("--  3) Thoát               --");
         System.out.println("-----------------------------");
         Scanner sc = new Scanner(System.in);
         System.out.print("Nhập lựa chọn: ");
         String n = sc.nextLine();
         while (!n.equals("1")&&!n.equals("2")&&!n.equals("3")){
             System.out.print("Nhập lại : ");
             n = sc.nextLine();
         }
         switch (n){
             case "1":
                 logicListUser.ShowListUser();
                 break;
             case "2":
                 /*System.out.println("Nhập email người dùng muốn xoá : ");
                 String email = sc.nextLine();*/
                 //email = sc.nextLine();
                 logicListUser.DeleteUserByEmail();
                 break;
             case "3":
                 MenuOptionAdmin menuOptionAdmin= new MenuOptionAdmin();
                 menuOptionAdmin.MenuOptionAdmin();
                 break;
         }
     }

}
