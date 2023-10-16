package com.booking.View.ViewForUser;

import com.booking.controller.LogicData.LogicFile;
import com.booking.model.User;

import java.io.FileNotFoundException;
import java.util.List;

import static com.booking.View.ViewMain.SignIn.signedIn;

public class Notifications {
    public void notifications() throws FileNotFoundException {
        System.out.println("-----------------------------------------");
        System.out.println("|-------------- THÔNG BÁO --------------|");
        System.out.println("-----------------------------------------");
        //System.out.println(signedIn.getNotification());
        LogicFile logicFile = new LogicFile();
        List<User> users = logicFile.ConvertFileToUser();
        for (User user : users)
            if(user.getEmail().equals(signedIn.getEmail())) {
                if (user.getNotification() == null) {
                    System.out.println("Không có thông báo. Đang quay trở lại màn hình chính...");
                }
                else System.out.println(user.getNotification());
            }
    }
}
