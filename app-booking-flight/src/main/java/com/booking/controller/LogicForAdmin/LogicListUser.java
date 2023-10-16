package com.booking.controller.LogicForAdmin;

import com.booking.View.ViewForAdmin.ManagementUser;
import com.booking.controller.LogicData.LogicFile;
import com.booking.controller.Regex.EmailRegex;
import com.booking.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LogicListUser {

    LogicFile logicFile = new LogicFile();

    public boolean CheckListUserNull() throws FileNotFoundException {
        List<User> users= logicFile.ConvertFileToUser();
        if(users.size()==0){
            return true;
        }
        return false;
    }

    public boolean CheckUserIsExist(String email) throws FileNotFoundException {
        List<User> users= logicFile.ConvertFileToUser();
        for (User user : users) {
            if(user.getEmail().contains(email)){
                return true;
            }
        }
        return false;
    }

    public void ShowListUser() throws IOException {
        List<User> users= logicFile.ConvertFileToUser();
        if(CheckListUserNull()){
            System.out.println("Danh sách người dùng đang rỗng . ");
            //trở về màn hình ...
            ManagementUser managementUser= new ManagementUser();
            managementUser.ViewManagement();
        }
        else {
            int count =1;
            for (User user : users) {
                if(user.getPosition_id()==1){
                    System.out.println("***************************");
                    System.out.println("STT : "+count);
                    System.out.println("Email : "+user.getEmail());
                    System.out.println("Password : "+user.getPassWord());
                    System.out.println("***************************");
                    count++;
                }
            }
            //trở về màn hnh ...
            System.out.println("Đang quay trở về màn hình tuỳ chọn...");
            ManagementUser managementUser= new ManagementUser();
            managementUser.ViewManagement();
        }
    }

    public void DeleteUserByEmail() throws IOException {
        Scanner sc = new Scanner(System.in);
        EmailRegex regex = new EmailRegex();
        System.out.println("Nhập email người dùng muốn xoá : ");
        String email = sc.nextLine();
        List<User> users= logicFile.ConvertFileToUser();
        if(!CheckUserIsExist(email)){
            System.out.println("Không tìm được user, user không tồn tại . ");
            //trở về màn hình ...
            ManagementUser managementUser = new ManagementUser();
            managementUser.ViewManagement();
        } else if (EmailRegex.emailRegex(email)) {
            System.out.println("Không đúng định dạng . ");
            //trở về màn hình ...
            System.out.println("Đang quay trở về màn hình tuỳ chọn...");
            ManagementUser managementUser = new ManagementUser();
            managementUser.ViewManagement();
        } else {
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email) && user.getPosition_id()==1){
                    users.remove(user);
                    logicFile.DeleteUserInFile(users);
                    System.out.println("Xóa thành công user : "+email);
                    break;
                }
            }
            //trở về màn hình ...
            System.out.println("Đang quay trở về màn hình tuỳ chọn...");
            ManagementUser managementUser = new ManagementUser();
            managementUser.ViewManagement();
        }
    }
}
