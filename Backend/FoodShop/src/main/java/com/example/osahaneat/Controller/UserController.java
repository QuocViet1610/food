package com.example.osahaneat.Controller;

import com.example.osahaneat.dto.UserDTO;
import com.example.osahaneat.enity.Users;
import com.example.osahaneat.payload.ResponseData;
import com.example.osahaneat.service.imp.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;


    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        ResponseData responseData=new ResponseData();
        responseData.setData(userServiceImp.getAllUser());
        return new ResponseEntity(responseData, HttpStatus.OK);
    }
//
    @PostMapping("/UserToken")
    public ResponseEntity<?> User(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        UserDTO userToken = userServiceImp.getUserId(id);

        responseData.setData(userToken);
            return new ResponseEntity(responseData, HttpStatus.OK);
    }
}
