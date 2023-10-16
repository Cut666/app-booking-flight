package com.booking.View.ViewMain;

import com.booking.View.ViewForAdmin.MenuOptionAdmin;
import com.booking.View.ViewForUser.MenuOptionUser;
import com.booking.controller.LogicAccount.Account;
import com.booking.controller.LogicData.LogicFile;
import com.booking.controller.Regex.EmailRegex;
import com.booking.model.Flight;
import com.booking.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SignIn {
    public static User signedIn = new User ();
    public void signIn() throws IOException {
        Scanner scan = new Scanner (System.in);
        Account account = new Account();
        System.out.println("-----------------------------------------");
        System.out.println("|---------------ĐĂNG NHẬP---------------|");
        System.out.println("-----------------------------------------");
        System.out.print("Địa chỉ email: ");
        String tempEmail = scan.nextLine();
        while (EmailRegex.emailRegex(tempEmail)) {
            System.out.println("Địa chỉ email chưa đúng định dạng. Vui lòng thử lại.");
            System.out.print("Địa chỉ email: ");
            tempEmail = scan.nextLine();
        }
        System.out.print("Mật khẩu: ");
        String tempPassword = scan.nextLine();
        if (!account.CheckUserPassword(tempEmail,tempPassword)){
            System.out.println("Mật khẩu chưa chính xác. Vui lòng đăng nhập lại ");
            MenuMain menuMain = new MenuMain();
            menuMain.DisplayMain();
        }
        else {
            signedIn.setEmail(tempEmail);
            signedIn.setPassWord(tempPassword);
            // dùng phân quyên để check admin hay user
            //nếu là 0 => admin  1=> user  2=> guest
            if(account.CheckPositionUser(tempEmail,tempPassword)){
                signedIn.setPosition_id(0);
                MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
                menuOptionAdmin.MenuOptionAdmin();
            }
            else {
                signedIn.setPosition_id(1);
                MenuOptionUser menuOptionUser = new MenuOptionUser();
                menuOptionUser.menuOptionUser();
            }
        }
    }
}
