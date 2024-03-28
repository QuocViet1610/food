package com.example.osahaneat.Controller;

import com.example.osahaneat.dto.CategoryDTO;
import com.example.osahaneat.payload.ResponseData;
import com.example.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryControll {

    @Autowired
    CategoryServiceImp categoryService;

    @GetMapping()
    public ResponseEntity<?> getHomePage(){
        ResponseData responseData = new ResponseData();
        List<CategoryDTO> Category = categoryService.getCategoryPageHome();
        responseData.setData(Category);
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    @GetMapping("/Bread")
    public ResponseEntity<?> getCategoryByBread(){
        ResponseData responseData = new ResponseData();
     CategoryDTO categoryDTO =categoryService.getCategoryByBread();
        responseData.setData(categoryDTO);
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        ResponseData responseData = new ResponseData();
        List<CategoryDTO> categoryDTO =categoryService.getAll();
        responseData.setData(categoryDTO);
        return new ResponseEntity(responseData, HttpStatus.OK);
    }


}
