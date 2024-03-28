package com.example.osahaneat.service.imp;

import com.example.osahaneat.dto.FoodDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {

    boolean save(MultipartFile file,
                 String titel,double price,  boolean is_freeship,int cated_id , int resId);

    List<FoodDTO> foodHomePage();

    List<FoodDTO> foodByRestaurant(int idRes);

    boolean delete(int id);

    boolean update(String titel,String img,  double price, boolean is_freeship, int cated_id, int resId);
}
