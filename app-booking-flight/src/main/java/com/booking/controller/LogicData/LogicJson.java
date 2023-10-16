package com.booking.controller.LogicData;

import com.booking.model.Flight;
import com.booking.model.User;
import com.google.gson.Gson;

public class LogicJson {
    public String ConvertObjectToStringJson(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
    public User ConvertStringJsonToObject(String json, User object){
        Gson gson = new Gson();
        object = gson.fromJson(json,User.class);
        return object;
    }
}
