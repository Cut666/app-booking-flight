package com.booking.View.ViewForUser;

import com.booking.controller.LogicAccount.Account;
import com.booking.controller.LogicForUser.LogicUserInfo;
import com.booking.controller.Regex.EmailRegex;
import com.booking.controller.Regex.PasswordRegex;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


import static com.booking.View.ViewMain.SignIn.signedIn;

public  class  DisplayInfo {
    public void displayInfo() {
        Scanner scan = new Scanner(System.in);
        String ans = "c";
        do {
            if (LogicUserInfo.verifyChoice1(ans)) {
                System.out.println("-----------------------------------------");
                System.out.println("|------ QUẢN LÝ THÔNG TIN CÁ NHÂN ------|");
                System.out.println("-----------------------------------------");
                System.out.println("Email: " + signedIn.getEmail());
                System.out.println("Mật khẩu: ********");
                System.out.println("*****************************************");
            }
            System.out.println("Bạn có muốn thay đổi thông tin cá nhân? " +
                    "Nhập C nếu có, nhập K nếu không.");
            ans = scan.nextLine();
            switch (ans) {
                case "C","c","1" -> {
                    int ans2;
                    do {
                        System.out.println("-----------------------------------------");
                        System.out.println("|------ THAY ĐỔI THÔNG TIN CÁ NHÂN -----|");
                        System.out.println("-----------------------------------------");
                        System.out.println("Vui lòng chọn nội dung muốn thay đổi.");
                        System.out.println("1. Email");
                        System.out.println("2. Mật khẩu");
                        System.out.println("3. Thoát");
                        ans2 = scan.nextInt();
                        while (LogicUserInfo.verifyChoice2(ans2)) {
                            System.out.println("Vui lòng chỉ chọn số từ 1 đến 3");
                            ans2 = scan.nextInt();
                        }
                        scan.nextLine();
                        if (ans2 == 3) {
                            break;
                        }
                        switch (ans2) {
                            case 1 -> editEmail();
                            case 2 -> editPassword();
                        }
                    } while (true);
                }
                case "K","k","0" -> {
                    System.out.println("Đang quay trở về màn hình chính...");
                    return;
                }
                default -> System.out.println("Vui lòng chỉ nhập \"C\" (thay đổi thông tin)" +
                        "hoặc \"K\" (quay trở về màn hình chính).");
            }
        } while (true);
    }
    Account account = new Account();
    public void editEmail() {
        Scanner scan = new Scanner (System.in);
        String tempEmail; int confirm;
        do {
            System.out.println("************* Thay đổi email ************");
            System.out.print("Nhập email mới: ");
            tempEmail = scan.nextLine();
            if (EmailRegex.emailRegex(tempEmail))
                System.out.println("Địa chỉ email chưa đúng định dạng. Vui lòng thử lại.");
            else {
                try {
                    if (!account.CheckEmailIsExist(tempEmail))
                        System.out.println("Địa chỉ email đã tồn tại. Vui lòng sử dụng email khác.");
                    else break;
                } catch (FileNotFoundException e) {
                    System.out.println("Đã có lỗi xảy ra");
                }
            }
        } while (true);

        System.out.println("Địa chỉ email của bạn sẽ được đổi thành \"" + tempEmail + "\".");
        System.out.println("Bạn có chắc muốn đổi email không? (Có bấm 1, Không bấm 0)");
        do {
            try {
                confirm = scan.nextInt();
                if (confirm != 1 && confirm != 0)
                    System.out.println("Vui lòng chỉ nhập giá trị 1 hoặc 0.");
                else break;
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng chỉ nhập giá trị 1 hoặc 0.");
            }
        } while (true);

        System.out.println("*****************************************");
        System.out.println("Đang đổi Email. 0% hoàn thành...");
        LogicUserInfo.editEmail(signedIn.getEmail(),tempEmail);
        System.out.println("*****************************************");
        signedIn.setEmail(tempEmail);
    }
    public void editPassword() {
        Scanner scan = new Scanner (System.in);
        String tempPassword, tempPassword2, verify;
        System.out.println("*********** Thay đổi mật khẩu ***********");
        System.out.print("Nhập mật khẩu cũ: ");
        verify = scan.nextLine();
        if (!verify.equals(signedIn.getPassWord())) {
            System.out.println("Mật khẩu sai. Quay lại màn hình trước...");
            return;
        }
        do {
            System.out.print("Nhập mật khẩu mới: ");
            tempPassword = scan.nextLine();
            if (PasswordRegex.passwordRegex(tempPassword)) {
                System.out.println("Mật khẩu chưa đủ độ bảo mật. Vui lòng thử lại.");
                System.out.println("Để tạo mật khẩu có độ bảo mật cao: ");
                System.out.println("- Mật khẩu phải chứa ít nhất 8 kí tự");
                System.out.println("- Mật khẩu phải sử dụng ít nhất một chữ cái viết thường, " +
                        "một chữ cái viết hoa, một chữ số và một ký tự đặc biệt " +
                        "(không phải chữ hoặc số)");
                continue;
            }
            else if (tempPassword.equals(signedIn.getPassWord())) {
                System.out.println("Không thể sử dụng lại mật khẩu cũ. Vui lòng thử lại.");
                continue;
            }
            System.out.println("Nhập lại mật khẩu mới: ");
            tempPassword2 = scan.nextLine();
            if (!tempPassword2.equals(tempPassword))
                System.out.println("Mật khẩu không khớp. Vui lòng nhập lại.");
            else break;
        } while (true);

        System.out.println("*****************************************");
        System.out.println("Đang đổi mật khẩu. 0% hoàn thành...");
        LogicUserInfo.editPassWord(signedIn.getPassWord(),tempPassword);
        System.out.println("*****************************************");
    }
}
