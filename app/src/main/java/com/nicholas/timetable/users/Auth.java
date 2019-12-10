package com.nicholas.timetable.users;

import java.util.ArrayList;
import java.util.List;

public class Auth {

    public static boolean isAuth = false;

    public static List<User> users = new ArrayList<User>();

    public static User currentUser;


    public static void fillUsersAuthData(){
        for(int i = 0; i < 10; i++){
            User user = new User();
            user.group = "П-311";
            user.name = String.format("Тестировщик %d", i + 1);
            user.login = String.format("user%d", i +1);
            user.password = String.format("pass%d", i + 1);
            users.add(user);
        }
    }

    public static boolean auth(String login, String password){
        for(User i : users){
            if(login.equals(i.login) && password.equals(i.password)) {
                currentUser = i;
                return true;
            }
        }
        return false;
    }




}
