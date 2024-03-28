package com.example.osahaneat.service;

import com.example.osahaneat.dto.UserDTO;
import com.example.osahaneat.enity.Roles;
import com.example.osahaneat.enity.Users;
import com.example.osahaneat.payload.request.SignUpRequest;
import com.example.osahaneat.repository.UserRepository;
import com.example.osahaneat.service.imp.LoginServiceImp;
import org.apache.catalina.User;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements LoginServiceImp { // alt + enter

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDTO> getAllUser(){
        List<Users> listUser=userRepository.findAll();
        List<UserDTO> listUserDTO= new ArrayList<>();
        for (Users userItem:listUser ) {
            UserDTO userDTO =new UserDTO();
            userDTO.setId(userItem.getId());
            userDTO.setUserName(userItem.getUserName());
            userDTO.setFullName(userItem.getFullName());
           
            System.out.println(userItem.getUserName());
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }

    @Override
    public boolean checkLogin(String username, String password) {
//        List<Users> listUser = userRepository.findByUserNameAndPassword(username,password) ;// tra ve mọt kieu list
        Users users=userRepository.findByUserName(username);
        return passwordEncoder.matches(password,users.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Users users=new Users();
        Roles roles=new Roles();
        roles.setId(signUpRequest.getRoleId());

        users.setUserName(signUpRequest.getGmail());
        users.setPassword(signUpRequest.getPassword());
        users.setFullName(signUpRequest.getFullName());
        users.setRole(roles);
        try{
            userRepository.save(users);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean signUp(String email, String FullName, String Password) {
        Users user = userRepository.findByUserName(email);

        if (user != null) {
            System.out.println("tai khoan da ton tai ");
            return false;

        } else {
            Users users=new Users();
            Roles roles=new Roles();
            users.setUserName(email);
            users.setFullName(FullName);
            users.setPassword(bCryptPasswordEncoder.encode(Password));
            roles.setId(1);
            users.setRole(roles);
            try{
                userRepository.save(users);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    @Override
    public Users findUser(String User) {
        Users user =userRepository.findByUserName(User);
        return user;
    }

    @Override
    public Users findUserById(int User) {
        Optional<Users> user = userRepository.findById(User);
        Users users = user.get();
        return users;
    }


}
