package utils;

import data.model.Users;
import dtos.RegisterUserRequest;

public class UserMapper {

    public static Users map(RegisterUserRequest registerUserRequest) {
        Users user = new Users();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        user.setPhoneNumber(registerUserRequest.getPhoneNumber());
        user.setLoggedIn(false);
        return user;
    }
}