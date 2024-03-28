package com.example.osahaneat.Controller;


import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.enity.Food;
import com.example.osahaneat.payload.ResponseData;
import com.example.osahaneat.service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    FoodServiceImp foodServiceImp;


    @PostMapping("/SaveFood")
    public ResponseEntity<?> createFood(@RequestParam MultipartFile file,@RequestParam String titel,
    @RequestParam Double price,@RequestParam boolean is_freeship,@RequestParam int cated_id,@RequestParam int resId ){
        System.out.println(titel);
        System.out.println(is_freeship);
        ResponseData responseData=new ResponseData();
        boolean isSuccess =  foodServiceImp.save(file,titel,price,is_freeship,cated_id,resId);

        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @GetMapping("/FoodPromotion")
    public ResponseEntity<?> getFoodPromotion(){
        ResponseData responseData=new ResponseData();

        responseData.setData(foodServiceImp.foodHomePage());

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/foodRestaurent")
    public ResponseEntity<?> getFoodByRestaurant(@RequestParam int id){
        ResponseData responseData=new ResponseData();
        List<FoodDTO> foodDTOList = foodServiceImp.foodByRestaurant(id);
        responseData.setData(foodDTOList);
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @PostMapping("/UpdateFood")
    public ResponseEntity<?> createFood(@RequestParam MultipartFile file){
        ResponseData responseData=new ResponseData();
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }



}
