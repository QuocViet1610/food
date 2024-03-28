package com.example.osahaneat.service.imp;

import com.example.osahaneat.dto.UserDTO;
import com.example.osahaneat.enity.Users;
import com.example.osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    public List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean addUser(SignUpRequest signUpRequest);
    boolean signUp(String email,String FullName ,String Password);
    Users findUser(String User);

    Users findUserById(int User);

}

