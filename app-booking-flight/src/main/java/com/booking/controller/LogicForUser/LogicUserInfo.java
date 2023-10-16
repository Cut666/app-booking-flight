package com.booking.controller.LogicForUser;

import com.booking.controller.LogicData.LogicFile;
import com.booking.controller.LogicData.LogicJson;
import com.booking.model.User;

import java.io.FileNotFoundException;
import java.util.List;

public class LogicUserInfo {
    public static boolean verifyChoice1(String input) {
        return input.equalsIgnoreCase("c") || input.equalsIgnoreCase("k")
                || input.equalsIgnoreCase("1") || input.equalsIgnoreCase("0");
        // Not one of the above characters => Return b = false => Won't trigger info display again.
    }
    public static boolean verifyChoice2(int input) {
        boolean b = input >= 1 && input <=3;
        return !b;
        // Not a number from 1 to 3 => b = false => Return true => Activate while loop
    }
    public static void editEmail(String oldE, String newE) {
        LogicFile logicFile = new LogicFile();
        LogicJson logicJson = new LogicJson();
        List<User> users;
        try {
            users = logicFile.ConvertFileToUser();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file.");
            return;
        }
        String password = null;
        String notifs = null;
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(oldE) && user.getPosition_id()==1){
                password = user.getPassWord();
                notifs = user.getNotification();
                users.remove(user);
                try {
                    logicFile.DeleteUserInFile(users);
                } catch (FileNotFoundException e) {
                    System.out.println("Không tìm thấy file.");
                    return;
                }
                System.out.println("Đang đổi Email. 50% hoàn thành...");
                // Just to confirm this process is performed correctly
                break;
            }
        }
        // Xóa người dùng cũ

        User user = new User();
        user.setEmail(newE);
        user.setPassWord(password);
        user.setNotification(notifs);
        user.setPosition_id(1);
        logicFile.WriteStringJsonToFile(logicJson.ConvertObjectToStringJson(user),"list_user.txt");
        System.out.println("Đang đổi Email. 100% hoàn thành.");
        System.out.println("Đổi địa chỉ email thành công!");
        // Thêm người dùng mới với email mới, password và perm không đổi
    }
    public static void editPassWord(String oldP,String newP) {
        LogicFile logicFile = new LogicFile();
        LogicJson logicJson = new LogicJson();
        List<User> users;
        try {
            users = logicFile.ConvertFileToUser();
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file.");
            return;
        }
        String notifs = null;
        String email = null;
        for (User user : users) {
            if (user.getPassWord().equals(oldP) && user.getPosition_id()==1){
                email = user.getEmail();
                notifs = user.getNotification();
                users.remove(user);
                try {
                    logicFile.DeleteUserInFile(users);
                } catch (FileNotFoundException e) {
                    System.out.println("Không tìm thấy file.");
                    return;
                }
                System.out.println("Đang đổi mật khẩu. 50% hoàn thành...");
                // Just to confirm this process is performed correctly
                break;
            }
        }
        // Xóa người dùng cũ

        User user = new User();
        user.setEmail(email);
        user.setPassWord(newP);
        user.setNotification(notifs);
        user.setPosition_id(1);
        logicFile.WriteStringJsonToFile(logicJson.ConvertObjectToStringJson(user),"list_user.txt");
        System.out.println("Đang đổi mật khẩu. 100% hoàn thành.");
        System.out.println("Đổi mật khẩu thành công!");
        // Thêm người dùng mới với mật khẩu mới, email và perm không đổi
    }
}
