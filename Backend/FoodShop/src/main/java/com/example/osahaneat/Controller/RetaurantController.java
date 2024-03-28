package com.example.osahaneat.Controller;


import com.example.osahaneat.dto.RestauranDetailDTO;
import com.example.osahaneat.dto.RestaurentDTO;
import com.example.osahaneat.payload.ResponseData;
import com.example.osahaneat.service.imp.FileServiceImp;
import com.example.osahaneat.service.imp.RestaurentSeviceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RetaurantController {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurentSeviceImp RestaurentSevice;

    @PostMapping("/uploadfile")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
     @RequestParam String titel,@RequestParam String subtitle, @RequestParam String descriptions, @RequestParam boolean is_freeship,@RequestParam String address,@RequestParam String open_date
    ){
        boolean saveRestaurent =RestaurentSevice.save(file,titel,subtitle,descriptions,is_freeship,address,open_date);
        ResponseData responseData = new ResponseData();
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileServiceImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRestauront(@RequestParam  MultipartFile file,@RequestParam  String titel,@RequestParam  String subtitle,@RequestParam  String descriptions,@RequestParam  boolean is_freeship,@RequestParam  String address,@RequestParam  String open_date){
        boolean Save = RestaurentSevice.save(file,titel,subtitle,descriptions,is_freeship,address,open_date);
        ResponseData responseData=new ResponseData();
        if(Save){
            responseData.setData(Save);
        }else {
            responseData.setData(Save);
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getHomePageUser(){
        ResponseData responseData = new ResponseData();
        List<RestaurentDTO> restaurentDTOList = RestaurentSevice.getHomePageRestaurent();
        if(restaurentDTOList.size() > 0 ){
            responseData.setData(restaurentDTOList);
        }else{
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }

    @GetMapping("/getrestaurantDetial")
    public ResponseEntity<?> getrestaurantDetial(@RequestParam int id ){
        ResponseData responseData = new ResponseData();
        RestauranDetailDTO restaurentDTOList = RestaurentSevice.getRestaurentDetail(id);

            responseData.setData(restaurentDTOList);

        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
}
