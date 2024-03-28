package com.example.osahaneat.Controller;

import com.example.osahaneat.payload.ResponseData;
import com.example.osahaneat.payload.request.OrderRequest;
import com.example.osahaneat.repository.OrderRepository;
import com.example.osahaneat.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        ResponseData responseData=new ResponseData();

        responseData.setData(orderServiceImp.save(orderRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
