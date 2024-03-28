package com.example.osahaneat.Controller;

import com.example.osahaneat.dto.UserDTO;
import com.example.osahaneat.payload.ResponseData;
import com.example.osahaneat.payload.request.SignUpRequest;
import com.example.osahaneat.service.imp.LoginServiceImp;
import com.example.osahaneat.service.imp.UserServiceImp;
import com.example.osahaneat.util.JwtUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@SpringBootApplication
@EnableWebSecurity
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp; // khi gọi Autowired tư dong hieu là lấy implement của class con

    @Autowired
    private JwtUtilHelper jwtUtilHelper;

    @Value("${jwt.privateKey}")
    private String privateKey;

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username,@RequestParam String password){


        ResponseData responseData=new ResponseData();
    if(loginServiceImp.checkLogin(username,password)){
        try{
            int userId =loginServiceImp.findUser(username).getId();
            String token = jwtUtilHelper.generateToken(userId);
            UserDTO userToken = userServiceImp.getUserId(userId);
            responseData.setData(token);

            responseData.setDescrip(userToken);
        }catch (Exception e){
            responseData.setData("false");
            responseData.setSuccess(false);
        }


    }else {
        responseData.setData("false");
        responseData.setSuccess(false);
    }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody SignUpRequest SignUpRequest){
//        ResponseData responseData=new ResponseData();
//        responseData.setData(loginServiceImp.addUser(SignUpRequest));
//        return new ResponseEntity(responseData, HttpStatus.OK);
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestParam String email,@RequestParam String FullName,@RequestParam String Password){
        ResponseData responseData=new ResponseData();
        System.out.println("dang ky");
        responseData.setData(loginServiceImp.signUp(email,FullName,Password ));
        return new ResponseEntity(responseData, HttpStatus.OK);
    }


}
