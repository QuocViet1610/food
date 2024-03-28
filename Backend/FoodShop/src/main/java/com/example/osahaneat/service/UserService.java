package com.example.osahaneat.service;

import com.example.osahaneat.dto.UserDTO;
import com.example.osahaneat.enity.Users;
import com.example.osahaneat.repository.UserRepository;
import com.example.osahaneat.service.imp.UserServiceImp;
import com.example.osahaneat.util.JwtUtilHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtilHelper jwtUtilHelper;
    @Override
    public List<UserDTO> getAllUser() {
        List<Users> listUsers=userRepository.findAll();
        List<UserDTO> listUsersDTO=new ArrayList<>();
        for (Users user:listUsers) {
            UserDTO userDTO=new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setFullName(user.getFullName());

            listUsersDTO.add(userDTO);
        }
        return listUsersDTO;
    }

    @Override
    public UserDTO getUserId(int id) {
        Optional<Users> userOptional = userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setFullName(user.getFullName());

            return userDTO;
        } else {

            return null;
        }

    }



}
