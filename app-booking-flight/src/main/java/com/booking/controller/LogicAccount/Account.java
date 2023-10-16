package com.booking.controller.LogicAccount;

import com.booking.controller.LogicData.LogicFile;
import com.booking.controller.LogicData.LogicJson;
import com.booking.model.User;


import java.io.FileNotFoundException;
import java.util.List;

public class Account {
     LogicFile logicFile = new LogicFile();
     LogicJson logicJson = new LogicJson();

    public boolean CheckUserPassword(String email, String passWord) throws FileNotFoundException {
        List<User> users= logicFile.ConvertFileToUser();
        for (User user : users) {
            if(user.getEmail().toLowerCase().equalsIgnoreCase(email.trim())&& user.getPassWord().equals(passWord)){
               return true;
            }
        }
        return false;
    }

    public boolean CheckPositionUser(String email,String passWord) throws FileNotFoundException {
        List<User> users= logicFile.ConvertFileToUser();
        for (User user : users) {
            if(user.getEmail().toLowerCase().equalsIgnoreCase(email.trim())&& user.getPassWord().toLowerCase().equalsIgnoreCase(passWord)){
                if(user.getPosition_id()==0){
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
    public boolean CheckEmailIsExist(String email) throws FileNotFoundException {
        List<User> users= logicFile.ConvertFileToUser();
        for (User user : users) {
            if(user.getEmail().equalsIgnoreCase(email.trim())&&user.getPosition_id()==1){
                return false;
            }
        }
        return true;
    }
    public void AddAccount(String email, String passWord, int perm){
        User user = new User();
        user.setEmail(email);
        user.setPassWord(passWord);
        user.setPosition_id(perm);
        logicFile.WriteStringJsonToFile(logicJson.ConvertObjectToStringJson(user),"list_user.txt");
    }
}
