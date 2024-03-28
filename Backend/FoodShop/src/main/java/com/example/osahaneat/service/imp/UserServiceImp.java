package com.example.osahaneat.service.imp;

import com.example.osahaneat.dto.UserDTO;
import com.example.osahaneat.enity.Users;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserServiceImp {
    public List<UserDTO> getAllUser();

    UserDTO getUserId(int id);
}
