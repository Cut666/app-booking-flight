package com.booking.View.ViewMain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.booking.controller.LogicAccount.Account;
import com.booking.controller.Regex.*;

public class Register {
    public void register() throws IOException {
        Scanner scan = new Scanner (System.in);
        Account account = new Account();
        System.out.println("-----------------------------------------");
        System.out.println("|-----------ĐĂNG KÝ TÀI KHOẢN-----------|");
        System.out.println("-----------------------------------------");
        int tempPerm = 1; //nếu là 0 => admin  1=> user  2=> guest

        String tempEmail;
        do {
            System.out.print("Địa chỉ email: ");
            tempEmail = scan.nextLine();
            while (EmailRegex.emailRegex(tempEmail)) {
                System.out.println("Địa chỉ email chưa đúng định dạng. Vui lòng thử lại.");
                System.out.print("Địa chỉ email: ");
                tempEmail = scan.nextLine();
            }
            if (!account.CheckEmailIsExist(tempEmail))
                System.out.println("Địa chỉ email đã tồn tại. Vui lòng sử dụng email khác.");
        } while (!account.CheckEmailIsExist(tempEmail));

        System.out.print("Mật khẩu: ");
        String tempPassword = scan.nextLine();
        while (PasswordRegex.passwordRegex(tempPassword)) {
            System.out.println("Mật khẩu chưa đủ độ bảo mật. Vui lòng thử lại.");
            System.out.println("Để tạo mật khẩu có độ bảo mật cao: ");
            System.out.println("- Mật khẩu phải chứa ít nhất 8 kí tự");
            System.out.println("- Mật khẩu phải sử dụng ít nhất một chữ cái viết thường, " +
                    "một chữ cái viết hoa, một chữ số và một ký tự đặc biệt " +
                    "(không phải chữ hoặc số)");
            System.out.print("Mật khẩu: ");
            tempPassword = scan.nextLine();
        }
        account.AddAccount(tempEmail,tempPassword,tempPerm);
        System.out.println("Đăng ký tài khoản người dùng thành công!");
        MenuMain menuMain = new MenuMain();
        menuMain.DisplayMain();
    }
}
